package com.jfb.digital_banking_data.entrypoint.message;

import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.core.usecase.account.DeleteAccountByIdUseCase;
import com.jfb.digital_banking_data.core.usecase.account.InsertAccountUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.jfb.digital_banking_data.utils.Constantes.*;
import static com.jfb.digital_banking_data.utils.Constantes.KAFKA_GROUP_ID_STRINGS;

@Service
public class KafkaAccountConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaAccountConsumerService.class);

    private final InsertAccountUseCase insertAccountUseCase;
    private final DeleteAccountByIdUseCase deleteAccountByIdUseCase;

    public KafkaAccountConsumerService(InsertAccountUseCase insertAccountUseCase, DeleteAccountByIdUseCase deleteAccountByIdUseCase) {
        this.insertAccountUseCase = insertAccountUseCase;
        this.deleteAccountByIdUseCase = deleteAccountByIdUseCase;
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

    @KafkaListener(
            topics = DELETE_ACCOUNT_KAFKA_TOPIC,
            groupId = KAFKA_GROUP_ID_STRINGS,
            containerFactory = "accountStringKafkaListenerContainerFactory")
    public void consumerDeleteAccount(String accountId) {
        try {
            logger.info("Mensagem recebida para exclusão lógica pelo Account ID: {}", accountId);
            deleteAccountByIdUseCase.execute(accountId);
            logger.info("Conta EXCLUIDA com sucesso, ACCOUNT ID: {}", accountId);
        } catch (Exception ex) {
            logger.error("Erro ao processar mensagem: {}", ex.getMessage(), ex);
        }
    }
}
