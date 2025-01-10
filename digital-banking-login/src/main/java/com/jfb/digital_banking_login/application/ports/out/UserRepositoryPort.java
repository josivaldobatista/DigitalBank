package com.jfb.digital_banking_login.application.ports.out;

import com.jfb.digital_banking_login.adapters.repositories.entity.UserEntity;
import com.jfb.digital_banking_login.domains.models.User;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<UserEntity> loadUserByUsername(String username);
    UserEntity save(UserEntity user);
}
