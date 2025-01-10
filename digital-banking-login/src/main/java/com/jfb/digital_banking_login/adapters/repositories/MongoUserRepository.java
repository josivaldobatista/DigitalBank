package com.jfb.digital_banking_login.adapters.repositories;

import com.jfb.digital_banking_login.adapters.repositories.entity.UserEntity;
import com.jfb.digital_banking_login.domains.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoUserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByCpfCnpj(String cpfCnpj);
}

