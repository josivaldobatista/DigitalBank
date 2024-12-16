package com.jfb.digital_banking_gateway.adapters.messaging;

import com.jfb.digital_banking_gateway.adapters.exceptions.CustomException;
import com.jfb.digital_banking_gateway.adapters.exceptions.ErrorMessageHolder;
import com.jfb.digital_banking_gateway.core.usecase.customer.interactors.InsertCustomerUseCaseImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.jfb.digital_banking_gateway.utils.Constantes.INSERT_CUSTOMER_ERROR_TOPIC;
import static com.jfb.digital_banking_gateway.utils.Constantes.KAFKA_GROUP_ID_OBJECTS;

@Service
public class KafkaErrorConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaErrorConsumerService.class);

    @KafkaListener(topics = INSERT_CUSTOMER_ERROR_TOPIC, groupId = KAFKA_GROUP_ID_OBJECTS, containerFactory = "errorKafkaListenerContainerFactory")
    public void consumeMessage(@Payload String message) {
        // Armazenar mensagem de erro para posterior utilização
        ErrorMessageHolder.setErrorMessage(message);
        logger.info("Mensagem de erro recebida: {}", message);

    }
}
