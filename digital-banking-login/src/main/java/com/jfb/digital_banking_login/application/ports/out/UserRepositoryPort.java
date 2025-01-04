package com.jfb.digital_banking_login.application.ports.out;

import com.jfb.digital_banking_login.domains.models.User;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> loadUserByUsername(String username);
    User save(User user);
}
