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
    public Optional<User> loadUserByUsername(String identifier) {
        return mongoUserRepository.findByUsername(identifier)
                .or(() -> mongoUserRepository.findByEmail(identifier))
                .or(() -> mongoUserRepository.findByCpfCnpj(identifier));
    }

    @Override
    public User save(User user) {
        return mongoUserRepository.save(user);
    }
}

