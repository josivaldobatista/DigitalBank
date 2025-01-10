package com.jfb.digital_banking_login.utils;

import com.jfb.digital_banking_login.adapters.repositories.entity.UserEntity;
import com.jfb.digital_banking_login.domains.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserCreator {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static UserEntity createUser(String username, String email, String cpf, String password) {
        var userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setCpfCnpj(cpf);
        userEntity.setPassword(passwordEncoder.encode(password));  // Codifica a senha
        userEntity.setRoles(List.of("ROLE_USER"));  // Define um papel padrão
        return userEntity;
    }

    public static UserEntity createAdminUser(String username, String email, String cpf, String password) {
        var userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setCpfCnpj(cpf);
        userEntity.setPassword(passwordEncoder.encode(password));  // Codifica a senha
        userEntity.setRoles(List.of("ROLE_ADMIN", "ROLE_USER"));  // Define papéis de administrador e usuário
        return userEntity;
    }
}
