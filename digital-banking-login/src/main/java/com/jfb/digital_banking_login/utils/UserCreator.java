package com.jfb.digital_banking_login.utils;

import com.jfb.digital_banking_login.domains.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserCreator {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));  // Codifica a senha
        user.setRoles(List.of("ROLE_USER"));  // Define um papel padrão
        return user;
    }
}
