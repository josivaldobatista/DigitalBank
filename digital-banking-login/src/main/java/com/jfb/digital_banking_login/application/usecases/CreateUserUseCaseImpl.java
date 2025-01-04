package com.jfb.digital_banking_login.application.usecases;

import com.jfb.digital_banking_login.application.ports.in.CreateUserUseCase;
import com.jfb.digital_banking_login.application.ports.out.UserRepositoryPort;
import com.jfb.digital_banking_login.adapters.controllers.request.CreateUserRequest;
import com.jfb.digital_banking_login.domains.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setCpfCnpj(request.cpfCnpj());
        user.setPassword(encodedPassword);
        user.setRoles(request.roles());

        // 4. Salvar o usuário no banco de dados
        userRepositoryPort.save(user);
    }
}