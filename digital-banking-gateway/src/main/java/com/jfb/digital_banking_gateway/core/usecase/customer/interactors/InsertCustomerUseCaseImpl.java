package com.jfb.digital_banking_gateway.core.usecase.customer.interactors;

import com.jfb.digital_banking_gateway.adapters.exceptions.CustomException;
import com.jfb.digital_banking_gateway.adapters.exceptions.ErrorMessageHolder;
import com.jfb.digital_banking_gateway.adapters.messaging.KafkaProducerService;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.usecase.customer.InsertCustomerUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.jfb.digital_banking_gateway.utils.Constantes.INSERT_CUSTOMER_KAFKA_TOPIC;

@Service
public class InsertCustomerUseCaseImpl implements InsertCustomerUseCase {

    private static final Logger logger = LoggerFactory.getLogger(InsertCustomerUseCaseImpl.class);

    private final KafkaProducerService kafkaProducerService;

    public InsertCustomerUseCaseImpl(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void execute(Customer customer) {
        // Limpar mensagem de erro antes de iniciar a operação
        ErrorMessageHolder.setErrorMessage(null);

        kafkaProducerService.sendMessage(INSERT_CUSTOMER_KAFKA_TOPIC, customer);
        logger.info("Send message to insert Customer: {}", customer);

        // Espera ativa por até 5 segundos para a mensagem de erro ser processada
        waitForErrorMessage();
    }

    private static void waitForErrorMessage() {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 5000) {
            if (ErrorMessageHolder.getErrorMessage() != null) {
                throw new CustomException(ErrorMessageHolder.getErrorMessage());
            }
            try {
                Thread.sleep(100);  // Checar a cada 100ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

}
