package com.jfb.digital_banking_data.entrypoint.message;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.jfb.digital_banking_data.utils.Constantes.INSERT_CUSTOMER_ERROR_TOPIC;

@Service
public class KafkaCustomerProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaCustomerProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendErrorMessage(String errorMessage) {
        kafkaTemplate.send(INSERT_CUSTOMER_ERROR_TOPIC, errorMessage);
    }
}


