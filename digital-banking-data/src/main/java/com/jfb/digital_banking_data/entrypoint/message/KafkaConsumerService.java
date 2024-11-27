package com.jfb.digital_banking_data.entrypoint.message;

import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.usecase.customer.InsertCustomerUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final InsertCustomerUseCase insertCustomerUseCase;

    public KafkaConsumerService(InsertCustomerUseCase insertCustomerUseCase) {
        this.insertCustomerUseCase = insertCustomerUseCase;
    }

    @KafkaListener(topics = "insert-customer-topic", groupId = "digital-banking-group")
    public void consumeInsertCustomer(Customer customer) {
        try {
            // Log quando uma mensagem é recebida
            logger.info("Mensagem recebida: {}", customer);

            // Chamar o serviço apropriado para salvar o cliente
            insertCustomerUseCase.execute(customer);

            // Log após salvar o cliente
            logger.info("Cliente salvo com sucesso: {}", customer);
        } catch (Exception e) {
            // Log em caso de exceção
            logger.error("Erro ao processar mensagem: {}", e.getMessage(), e);
        }
    }
}
