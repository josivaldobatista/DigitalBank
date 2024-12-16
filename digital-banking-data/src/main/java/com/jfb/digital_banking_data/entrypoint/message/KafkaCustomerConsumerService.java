package com.jfb.digital_banking_data.entrypoint.message;

import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.exception.ObjectAlreadyExistsException;
import com.jfb.digital_banking_data.core.usecase.customer.DeleteCustomerUseCase;
import com.jfb.digital_banking_data.core.usecase.customer.InsertCustomerUseCase;
import com.jfb.digital_banking_data.core.usecase.customer.UpdateCustomerUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.jfb.digital_banking_data.utils.Constantes.*;

@Service
public class KafkaCustomerConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaCustomerConsumerService.class);

    private final InsertCustomerUseCase insertCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final KafkaCustomerProducerService kafkaCustomerProducerService;

    public KafkaCustomerConsumerService(
            InsertCustomerUseCase insertCustomerUseCase,
            DeleteCustomerUseCase deleteCustomerUseCase,
            UpdateCustomerUseCase updateCustomerUseCase,
            KafkaCustomerProducerService kafkaCustomerProducerService) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.kafkaCustomerProducerService = kafkaCustomerProducerService;
    }

    @KafkaListener(topics = INSERT_CUSTOMER_KAFKA_TOPIC, groupId = KAFKA_GROUP_ID_OBJECTS, containerFactory = "kafkaListenerContainerFactory")
    public void consumeInsertCustomer(@Payload Customer customer, Acknowledgment acknowledgment) {
        try {
            insertCustomerUseCase.execute(customer);
            acknowledgment.acknowledge();
        } catch (ObjectAlreadyExistsException ex) {
            // Enviar mensagem de erro ao novo tópico
            kafkaCustomerProducerService.sendErrorMessage("Customer já existe, CPF/CNPJ: " + customer.getCpfCnpj());
            acknowledgment.acknowledge();
        } catch (Exception ex) {
            System.out.println("Erro inesperado: " + ex.getMessage());
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(
            topics = DELETE_CUSTOMER_KAFKA_TOPIC,
            groupId = KAFKA_GROUP_ID_STRINGS,
            containerFactory = "stringKafkaListenerContainerFactory")
    public void consumerDeleteCustomer(String customerId) {
        try {
            logger.info("Mensagem recebida para exclusão lógica pelo Customer ID: {}", customerId);
            deleteCustomerUseCase.execute(customerId);
            logger.info("Cliente INATIVADO, customer ID: {}", customerId);
        } catch (Exception ex) {
            logger.error("Erro ao processar mensagem: {}", ex.getMessage(), ex);
        }
    }

    @KafkaListener(
            topics = UPDATE_CUSTOMER_KAFKA_TOPIC,
            groupId = KAFKA_GROUP_ID_OBJECTS,
            containerFactory = "kafkaListenerContainerFactory")
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
