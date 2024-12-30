package com.jfb.digital_banking_login.adapters.controllers.request;

public record LoginRequest(
        String username,
        String email,
        String cpfCnpj,
        String password
) {}
