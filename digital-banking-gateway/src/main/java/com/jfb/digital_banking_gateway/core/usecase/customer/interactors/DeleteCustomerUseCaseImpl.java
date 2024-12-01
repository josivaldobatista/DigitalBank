package com.jfb.digital_banking_gateway.core.usecase.customer.interactors;

import com.jfb.digital_banking_gateway.adapters.messaging.KafkaProducerService;
import com.jfb.digital_banking_gateway.core.usecase.customer.DeleteCustomerUseCase;
import org.springframework.stereotype.Service;

import static com.jfb.digital_banking_gateway.utils.Constantes.DELETE_CUSTOMER_KAFKA_TOPIC;

@Service
public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private final KafkaProducerService kafkaProducerService;

    public DeleteCustomerUseCaseImpl(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void execute(String customerId) {
        kafkaProducerService.sendMessage(DELETE_CUSTOMER_KAFKA_TOPIC, customerId);
    }
}

