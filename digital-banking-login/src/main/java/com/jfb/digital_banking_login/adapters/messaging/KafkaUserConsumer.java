package com.jfb.digital_banking_login.adapters.messaging;

import com.jfb.digital_banking_login.adapters.repositories.entity.UserEntity;
import com.jfb.digital_banking_login.application.ports.out.UserRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.jfb.digital_banking_login.utils.Constantes.INSERT_USER_KAFKA_TOPIC;
import static com.jfb.digital_banking_login.utils.Constantes.KAFKA_GROUP_ID_OBJECTS;

@EnableKafka
@Configuration
public class KafkaUserConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaUserConsumer.class);

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injetando o PasswordEncoder

    @KafkaListener(topics = INSERT_USER_KAFKA_TOPIC, groupId = KAFKA_GROUP_ID_OBJECTS, containerFactory = "kafkaListenerContainerFactory")
    public void consumeInsertUser(@Payload UserEntity userEntity, Acknowledgment acknowledgment) {
        try {
            // Criptografa a senha antes de salvar
            String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(encodedPassword);

            // Salva no banco de dados
            userRepositoryPort.save(userEntity);
            acknowledgment.acknowledge();
        } catch (Exception ex) {
            logger.error("Erro ao processar a mensagem: ", ex);
            acknowledgment.acknowledge();
        }
    }
}
