package com.jfb.digital_banking_gateway.adapters.messaging;

import org.apache.kafka.clients.producer.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Object message, Callback callback) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                if (callback != null) {
                    callback.onCompletion(null, new Exception(ex));
                }
                logger.error("Erro ao enviar mensagem para o tópico {}: {}", topic, ex.getMessage());
            } else {
                if (callback != null) {
                    callback.onCompletion(result.getRecordMetadata(), null);
                }
                logger.info("Mensagem enviada com sucesso para o tópico {}: {}", topic, message);
            }
        });
    }

    public void sendMessage(String topic, Object message) {
        sendMessage(topic, message, null);
    }
}
