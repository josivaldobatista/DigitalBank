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
        Optional<User> existingAdmin = userRepository.findByUsername("admin");

        if (existingUser1.isEmpty() && existingAdmin.isEmpty()) {
            // Criação de usuários fictícios
            User user1 = UserCreator.createUser("user1", "user1@example.com", "12345678901", "password1");
            User user2 = UserCreator.createUser("user2", "user2@example.com", "12345678902", "password2");
            User admin = UserCreator.createAdminUser("admin", "admin@example.com", "12345678903", "admin");

            // Salvando usuários no repositório
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(admin);

            logger.info("Usuários iniciais criados com sucesso!");
        } else {
            logger.info("Usuários já existem no banco de dados, não será necessário criar novamente.");
        }
    }
}
