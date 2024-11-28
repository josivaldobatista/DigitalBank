package com.jfb.digital_banking_gateway.core.usecase.interactors;


import com.jfb.digital_banking_gateway.adapters.messaging.KafkaProducerService;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.usecase.InsertCustomerUseCase;
import org.springframework.stereotype.Service;

@Service
public class InsertCustomerUseCaseImpl implements InsertCustomerUseCase {

    private final KafkaProducerService kafkaProducerService;

    public InsertCustomerUseCaseImpl(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void execute(Customer customer) {
        kafkaProducerService.sendMessage("insert-customer-topic", customer);
    }
}

