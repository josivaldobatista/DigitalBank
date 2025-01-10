package com.jfb.digital_banking_gateway.core.usecase.customer.interactors;

import com.jfb.digital_banking_gateway.adapters.exceptions.CustomException;
import com.jfb.digital_banking_gateway.adapters.exceptions.ErrorMessageHolder;
import com.jfb.digital_banking_gateway.adapters.messaging.KafkaErrorConsumerService;
import com.jfb.digital_banking_gateway.adapters.messaging.KafkaProducerService;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.domain.models.User;
import com.jfb.digital_banking_gateway.core.usecase.customer.InsertCustomerUseCase;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.jfb.digital_banking_gateway.utils.Constantes.INSERT_CUSTOMER_KAFKA_TOPIC;
import static com.jfb.digital_banking_gateway.utils.Constantes.INSERT_USER_KAFKA_TOPIC;

@Service
public class InsertCustomerUseCaseImpl implements InsertCustomerUseCase {

    private static final Logger logger = LoggerFactory.getLogger(InsertCustomerUseCaseImpl.class);
    private static final String ROLE_USER = "ROLE_USER";

    @Value("${kafka.timeout.ms:300}")
    private long kafkaTimeoutMs;

    private final KafkaProducerService kafkaProducerService;

    public InsertCustomerUseCaseImpl(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void execute(Customer customer) {
        validateCustomer(customer);

        CompletableFuture<Void> customerFuture = sendMessageWithTimeout(INSERT_CUSTOMER_KAFKA_TOPIC, customer);
        CompletableFuture<Void> userFuture = sendMessageWithTimeout(INSERT_USER_KAFKA_TOPIC, createUserFromCustomer(customer));

        waitForResponse(customerFuture);
        waitForResponse(userFuture);

        logger.info("Solicitação de inserção de cliente enviada: {}", customer);
    }

    private void validateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer não pode ser nulo");
        }
        if (customer.getName() == null || customer.getEmail() == null || customer.getCpfCnpj() == null) {
            throw new IllegalArgumentException("Campos obrigatórios do Customer não podem ser nulos");
        }
    }

    private CompletableFuture<Void> sendMessageWithTimeout(String topic, Object message) {
        CompletableFuture<Void> future = new CompletableFuture<>();

        kafkaProducerService.sendMessage(topic, message, (metadata, exception) -> {
            if (exception != null) {
                future.completeExceptionally(exception);
            } else {
                future.complete(null);
            }
        });

        return future;
    }

    private void waitForResponse(CompletableFuture<Void> future) {
        try {
            future.get(kafkaTimeoutMs, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            throw new CustomException("Timeout ao enviar mensagem para o Kafka" + e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Operação interrompida", e);
        } catch (ExecutionException e) {
            throw new CustomException("Erro ao enviar mensagem para o Kafka" + e.getCause());
        }
    }

    private User createUserFromCustomer(Customer customer) {
        User user = new User();
        user.setUsername(customer.getName());
        user.setEmail(customer.getEmail());
        user.setUsername(customer.getUsername());
        user.setPassword(customer.getPassword());
        user.setCpfCnpj(customer.getCpfCnpj());
        user.setRoles(List.of(ROLE_USER));
        return user;
    }
}