package com.jfb.digital_banking_login.application.usecases;

import com.jfb.digital_banking_login.adapters.controllers.request.LoginRequest;
import com.jfb.digital_banking_login.adapters.controllers.response.LoginResponse;
import com.jfb.digital_banking_login.adapters.securities.JwtTokenProvider;
import com.jfb.digital_banking_login.application.ports.in.LoginUseCase;
import com.jfb.digital_banking_login.application.ports.out.UserRepositoryPort;
import com.jfb.digital_banking_login.domains.exceptions.UserNotFoundException;
import com.jfb.digital_banking_login.domains.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {

    private static final Logger logger = LoggerFactory.getLogger(LoginUseCaseImpl.class);

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
    public LoginResponse execute(LoginRequest loginRequest) {
        validateLoginRequest(loginRequest);
        logger.info("Iniciando o processo de autenticação.");

        String identifier = extractIdentifier(loginRequest);
        User user = findUserByIdentifier(identifier);

        authenticateUser(identifier, loginRequest.password());
        String token = generateToken(user);

        logger.info("Login realizado com sucesso para o usuário: {}", user.getUsername());
        return new LoginResponse(token);
    }

    private void validateLoginRequest(LoginRequest loginRequest) {
        if (loginRequest == null || loginRequest.password() == null) {
            logger.error("LoginRequest inválido ou senha não fornecida.");
            throw new IllegalArgumentException("LoginRequest ou senha são obrigatórios.");
        }
    }

    private String extractIdentifier(LoginRequest loginRequest) {
        String identifier = loginRequest.username() != null ? loginRequest.username() :
                loginRequest.email() != null ? loginRequest.email() :
                        loginRequest.cpfCnpj();

        if (identifier == null) {
            logger.error("Nenhum identificador válido foi fornecido. É necessário informar username, email ou CPF.");
            throw new IllegalArgumentException("Um identificador (username, email ou cpf) deve ser fornecido.");
        }

        logger.debug("Identificador fornecido: {}", identifier);
        return identifier;
    }

    private User findUserByIdentifier(String identifier) {
        return userRepositoryPort.loadUserByUsername(identifier)
                .orElseThrow(() -> {
                    logger.warn("Usuário não encontrado para o identificador: {}", identifier);
                    return new UserNotFoundException("Usuário não encontrado");
                });
    }

    private void authenticateUser(String identifier, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(identifier, password)
            );

            if (!authentication.isAuthenticated()) {
                logger.error("Falha na autenticação para o identificador: {}", identifier);
                throw new RuntimeException("Falha na autenticação");
            }

            logger.info("Autenticação bem-sucedida para o identificador: {}", identifier);
        } catch (AuthenticationException e) {
            logger.error("Erro de autenticação: {}", e.getMessage());
            throw new RuntimeException("Erro durante a autenticação", e);
        }
    }

    private String generateToken(User user) {
        String token = jwtTokenProvider.createToken(user.getUsername());
        logger.debug("Token JWT gerado com sucesso para o usuário: {}", user.getUsername());
        return token;
    }
}
