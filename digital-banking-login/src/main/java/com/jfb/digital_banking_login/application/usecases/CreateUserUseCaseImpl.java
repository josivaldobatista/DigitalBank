package com.jfb.digital_banking_login.application.usecases;

import com.jfb.digital_banking_login.adapters.repositories.entity.UserEntity;
import com.jfb.digital_banking_login.application.ports.in.CreateUserUseCase;
import com.jfb.digital_banking_login.application.ports.out.UserRepositoryPort;
import com.jfb.digital_banking_login.adapters.controllers.request.CreateUserRequest;
import com.jfb.digital_banking_login.domains.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void execute(CreateUserRequest request) {
        // 1. Validar os dados da requisição (implementar validações)

        // 2. Criptografar a senha
        String encodedPassword = passwordEncoder.encode(request.password());

        // 3. Criar o objeto User
        var userEntity = new UserEntity();
        userEntity.setUsername(request.username());
        userEntity.setEmail(request.email());
        userEntity.setCpfCnpj(request.cpfCnpj());
        userEntity.setPassword(encodedPassword);

        // Ajustar a lógica para as roles (utilizando Set para evitar duplicatas)
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER"); // Adicionar ROLE_USER como padrão

        if (request.roles() != null) {
            roles.addAll(request.roles()); // Adicionar todas as roles da requisição
        }

        userEntity.setRoles(new ArrayList<>(roles)); // Converter Set para List

        // 4. Salvar o usuário no banco de dados
        userRepositoryPort.save(userEntity);
    }

}