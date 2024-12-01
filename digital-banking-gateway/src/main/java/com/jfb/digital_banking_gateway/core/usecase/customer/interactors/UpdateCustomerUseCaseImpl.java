package com.jfb.digital_banking_gateway.core.usecase.customer.interactors;


import com.jfb.digital_banking_gateway.adapters.messaging.KafkaProducerService;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.usecase.customer.UpdateCustomerUseCase;
import org.springframework.stereotype.Service;

import static com.jfb.digital_banking_gateway.utils.Constantes.UPDATE_CUSTOMER_KAFKA_TOPIC;

@Service
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final KafkaProducerService kafkaProducerService;

    public UpdateCustomerUseCaseImpl(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void execute(Customer model) {
        kafkaProducerService.sendMessage(UPDATE_CUSTOMER_KAFKA_TOPIC, model);
    }
}
