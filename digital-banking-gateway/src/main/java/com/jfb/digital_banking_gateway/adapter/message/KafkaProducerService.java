package com.jfb.digital_banking_gateway.adapter.message;

import com.jfb.digital_banking_gateway.adapter.dto.CustomerDTO;
import com.jfb.digital_banking_gateway.domain.port.out.MessageOutputPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService implements MessageOutputPort {

    private final KafkaTemplate<String, CustomerDTO> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, CustomerDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, CustomerDTO customerDTO) {
        kafkaTemplate.send(topic, customerDTO);
    }
}
