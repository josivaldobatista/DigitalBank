package com.jfb.digital_banking_gateway.adapters.controllers.request;

import com.jfb.digital_banking_gateway.core.domain.models.Status;

import java.time.LocalDate;

public record CustomerRequest(
        String name,
        String email,
        LocalDate birthDate,
        String cpfCnpj,
        Status status
) {}

