package com.jfb.digital_banking_gateway.adapters.messaging;

import com.jfb.digital_banking_gateway.adapters.exceptions.ErrorMessageHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

import static com.jfb.digital_banking_gateway.utils.Constantes.INSERT_CUSTOMER_ERROR_TOPIC;
import static com.jfb.digital_banking_gateway.utils.Constantes.KAFKA_GROUP_ID_OBJECTS;

@Service
public class KafkaErrorConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaErrorConsumerService.class);

    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = INSERT_CUSTOMER_ERROR_TOPIC, groupId = KAFKA_GROUP_ID_OBJECTS, containerFactory = "errorKafkaListenerContainerFactory")
    public void consumeMessage(@Payload String message) {
        // Armazenar mensagem de erro para posterior utilização
        ErrorMessageHolder.setErrorMessage(message);
        latch.countDown();
        logger.info("Mensagem de erro recebida: {}", message);
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }
}
