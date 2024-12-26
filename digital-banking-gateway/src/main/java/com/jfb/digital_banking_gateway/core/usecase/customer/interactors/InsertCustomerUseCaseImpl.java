package com.jfb.digital_banking_gateway.core.usecase.customer.interactors;

import com.jfb.digital_banking_gateway.adapters.exceptions.CustomException;
import com.jfb.digital_banking_gateway.adapters.exceptions.ErrorMessageHolder;
import com.jfb.digital_banking_gateway.adapters.messaging.KafkaErrorConsumerService;
import com.jfb.digital_banking_gateway.adapters.messaging.KafkaProducerService;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.usecase.customer.InsertCustomerUseCase;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.jfb.digital_banking_gateway.utils.Constantes.INSERT_CUSTOMER_KAFKA_TOPIC;

@Service
public class InsertCustomerUseCaseImpl implements InsertCustomerUseCase {

    private static final Logger logger = LoggerFactory.getLogger(InsertCustomerUseCaseImpl.class);

    private final KafkaProducerService kafkaProducerService;
    private final KafkaErrorConsumerService kafkaErrorConsumerService;

    public InsertCustomerUseCaseImpl(KafkaProducerService kafkaProducerService, KafkaErrorConsumerService kafkaErrorConsumerService) {
        this.kafkaProducerService = kafkaProducerService;
        this.kafkaErrorConsumerService = kafkaErrorConsumerService;
    }

    @Override
    public void execute(Customer customer) {
        // Limpar mensagem de erro antes de iniciar a operação
        ErrorMessageHolder.clear();
        kafkaErrorConsumerService.resetLatch();

        kafkaProducerService.sendMessage(
                INSERT_CUSTOMER_KAFKA_TOPIC,
                customer,
                new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (exception != null) {
                            logger.error("Erro ao enviar mensagem para o Kafka", exception);
                            // Armazena a mensagem de erro no ErrorMessageHolder
                            ErrorMessageHolder.setErrorMessage(exception.getMessage());
                            kafkaErrorConsumerService.getLatch().countDown(); // Garante que o latch seja liberado em caso de erro
                        } else {
                            logger.info("Mensagem enviada com sucesso para o Kafka. Metadata: {}", metadata);
                        }
                    }
                }
        );

        try {
            // Aguarda até 300ms para que a mensagem de erro seja consumida
            kafkaErrorConsumerService.getLatch().await(300, TimeUnit.MILLISECONDS);
            // Verifica se houve erro e lança exceção se necessário
            String errorMessage = ErrorMessageHolder.getErrorMessage();
            if (errorMessage != null) {
                throw new CustomException("Erro ao inserir cliente: " + errorMessage);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Erro ao esperar a resposta do Kafka", e);
        }

        logger.info("Solicitação de inserção de cliente enviada: {}", customer);
    }
}
