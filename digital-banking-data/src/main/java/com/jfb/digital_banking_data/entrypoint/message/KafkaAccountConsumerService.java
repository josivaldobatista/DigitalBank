package com.jfb.digital_banking_data.entrypoint.message;

import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.usecase.account.InsertAccountUseCase;
import com.jfb.digital_banking_data.core.usecase.customer.InsertCustomerUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.jfb.digital_banking_data.utils.Constantes.INSERT_ACCOUNT_KAFKA_TOPIC;
import static com.jfb.digital_banking_data.utils.Constantes.KAFKA_GROUP_ID_OBJECTS;

@Service
public class KafkaAccountConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaAccountConsumerService.class);

    private final InsertAccountUseCase insertAccountUseCase;

    public KafkaAccountConsumerService(InsertAccountUseCase insertAccountUseCase) {
        this.insertAccountUseCase = insertAccountUseCase;
    }

    @KafkaListener(
            topics = INSERT_ACCOUNT_KAFKA_TOPIC,
            groupId = KAFKA_GROUP_ID_OBJECTS,
            containerFactory = "kafkaAccountListenerContainerFactory")
    public void consumeInsertCustomer(Account account) {
        try {
            logger.info("Mensagem recebida: {}", account);
            insertAccountUseCase.execute(account);
            logger.info("Account salvo com sucesso: {}", account);
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem: {}", e.getMessage(), e);
        }
    }
}
