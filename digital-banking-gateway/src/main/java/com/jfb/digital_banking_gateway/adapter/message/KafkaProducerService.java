package com.jfb.digital_banking_gateway.adapter.message;

import com.jfb.digital_banking_gateway.adapter.request.CustomerRequest;
import com.jfb.digital_banking_gateway.domain.port.out.MessageOutputPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService implements MessageOutputPort {

    private final KafkaTemplate<String, CustomerRequest> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, CustomerRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, CustomerRequest customerDTO) {
        kafkaTemplate.send(topic, customerDTO);
    }
}
