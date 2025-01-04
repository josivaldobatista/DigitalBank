package com.jfb.digital_banking_login.adapters.controllers.request;

import java.util.List;

public record CreateUserRequest(
        String username,
        String email,
        String cpfCnpj,
        String password,
        List<String> roles
) {}