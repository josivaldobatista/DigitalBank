package com.jfb.digital_banking_login.adapters.repositories;

import com.jfb.digital_banking_login.domains.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoUserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByCpfCnpj(String cpfCnpj);
}

