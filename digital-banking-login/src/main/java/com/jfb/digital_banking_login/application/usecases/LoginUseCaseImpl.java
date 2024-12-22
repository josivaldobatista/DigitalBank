package com.jfb.digital_banking_login.application.usecases;

import com.jfb.digital_banking_login.adapters.controllers.request.LoginRequest;
import com.jfb.digital_banking_login.adapters.securities.JwtTokenProvider;
import com.jfb.digital_banking_login.application.ports.in.LoginUseCase;
import com.jfb.digital_banking_login.application.ports.out.UserRepositoryPort;
import com.jfb.digital_banking_login.domains.exceptions.UserNotFoundException;
import com.jfb.digital_banking_login.domains.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginUseCaseImpl(
            UserRepositoryPort userRepositoryPort,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider) {
        this.userRepositoryPort = userRepositoryPort;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String execute(LoginRequest loginRequest) {
        String username = loginRequest.username();
        String password = loginRequest.password();

        System.out.println("Iniciando autenticação para usuário: " + username);

        User user = userRepositoryPort.loadUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        System.out.println("Usuário encontrado: " + user.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        System.out.println("Autenticação bem-sucedida para usuário: " + username);

        if (authentication.isAuthenticated()) {
            return jwtTokenProvider.createToken(user.getUsername());
        } else {
            throw new RuntimeException("Falha na autenticação");
        }
    }

}
