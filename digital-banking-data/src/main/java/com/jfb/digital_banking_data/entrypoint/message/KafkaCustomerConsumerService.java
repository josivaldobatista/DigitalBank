package com.jfb.digital_banking_data.entrypoint.message;

import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.usecase.customer.DeleteCustomerUseCase;
import com.jfb.digital_banking_data.core.usecase.customer.InsertCustomerUseCase;
import com.jfb.digital_banking_data.core.usecase.customer.UpdateCustomerUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.jfb.digital_banking_data.utils.Constantes.*;

@Service
public class KafkaCustomerConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaCustomerConsumerService.class);

    private final InsertCustomerUseCase insertCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    public KafkaCustomerConsumerService(InsertCustomerUseCase insertCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase, UpdateCustomerUseCase updateCustomerUseCase) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
    }

    @KafkaListener(topics = INSERT_CUSTOMER_KAFKA_TOPIC, groupId = KAFKA_GROUP_ID_OBJECTS, containerFactory = "kafkaListenerContainerFactory")
    public void consumeInsertCustomer(Customer customer) {
        try {
            logger.info("Mensagem recebida: {}", customer);
            insertCustomerUseCase.execute(customer);
            logger.info("Cliente salvo com sucesso: {}", customer);
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem: {}", e.getMessage(), e);
        }
    }

    @KafkaListener(topics = DELETE_CUSTOMER_KAFKA_TOPIC, groupId = KAFKA_GROUP_ID_STRINGS, containerFactory = "stringKafkaListenerContainerFactory")
    public void consumerDeleteCustomer(String customerId) {
        deleteCustomerUseCase.execute(customerId);
    }

    @KafkaListener(topics = UPDATE_CUSTOMER_KAFKA_TOPIC, groupId = KAFKA_GROUP_ID_OBJECTS, containerFactory = "kafkaListenerContainerFactory")
    public void consumeUpdateCustomer(Customer customer) {
        try {
            logger.info("Mensagem recebida para atualização: {}", customer);
            updateCustomerUseCase.execute(customer, customer.getId());
            logger.info("Cliente atualizado com sucesso: {}", customer);
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem: {}", e.getMessage(), e);
        }
    }
}
