package com.jfb.digital_banking_gateway.core.usecase.account.interactors;

import com.jfb.digital_banking_gateway.adapters.messaging.KafkaProducerService;
import com.jfb.digital_banking_gateway.core.domain.models.Account;
import com.jfb.digital_banking_gateway.core.usecase.account.InsertAccountUseCase;
import org.springframework.stereotype.Component;

import static com.jfb.digital_banking_gateway.utils.Constantes.INSERT_ACCOUNT_KAFKA_TOPIC;

@Component
public class InsertAccountUseCaseImpl implements InsertAccountUseCase {

    private final KafkaProducerService kafkaProducerService;

    public InsertAccountUseCaseImpl(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void execute(Account model) {
        kafkaProducerService.sendMessage(INSERT_ACCOUNT_KAFKA_TOPIC, model);
    }
}
