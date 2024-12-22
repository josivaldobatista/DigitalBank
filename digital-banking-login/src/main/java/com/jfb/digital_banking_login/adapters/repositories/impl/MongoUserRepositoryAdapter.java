package com.jfb.digital_banking_login.adapters.repositories.impl;

import com.jfb.digital_banking_login.adapters.repositories.MongoUserRepository;
import com.jfb.digital_banking_login.application.ports.out.UserRepositoryPort;
import com.jfb.digital_banking_login.domains.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoUserRepositoryAdapter implements UserRepositoryPort {

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @Override
    public Optional<User> loadUserByUsername(String username) {
        return mongoUserRepository.findByUsername(username);
    }
}
