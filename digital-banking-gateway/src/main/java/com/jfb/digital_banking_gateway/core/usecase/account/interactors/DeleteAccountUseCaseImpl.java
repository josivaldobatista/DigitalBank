package com.jfb.digital_banking_gateway.core.usecase.account.interactors;

import com.jfb.digital_banking_gateway.adapters.messaging.KafkaProducerService;
import com.jfb.digital_banking_gateway.core.usecase.account.DeleteAccountUseCase;
import org.springframework.stereotype.Service;

import static com.jfb.digital_banking_gateway.utils.Constantes.DELETE_ACCOUNT_KAFKA_TOPIC;

@Service
public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {

    private final KafkaProducerService kafkaProducerService;

    public DeleteAccountUseCaseImpl(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void execute(String customerId) {
        kafkaProducerService.sendMessage(DELETE_ACCOUNT_KAFKA_TOPIC, customerId);
    }
}

