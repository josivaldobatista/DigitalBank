package com.jfb.digital_banking_login;

import com.jfb.digital_banking_login.domains.models.User;
import com.jfb.digital_banking_login.adapters.repositories.MongoUserRepository;
import com.jfb.digital_banking_login.utils.UserCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@SpringBootApplication
public class DigitalBankingLoginApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DigitalBankingLoginApplication.class);

    @Autowired
    private MongoUserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingLoginApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Verificar se os usuários já existem
        Optional<User> existingUser1 = userRepository.findByUsername("user1");
        Optional<User> existingUser2 = userRepository.findByUsername("user2");

        if (existingUser1.isEmpty() && existingUser2.isEmpty()) {
            User user1 = UserCreator.createUser("user1", "password1");
            User user2 = UserCreator.createUser("user2", "password2");

            userRepository.save(user1);
            userRepository.save(user2);

            logger.info("Usuários iniciais criados com sucesso!");
        } else {
            logger.info("Usuários já existem no banco de dados, não será necessário criar novamente.");
        }
    }
}
