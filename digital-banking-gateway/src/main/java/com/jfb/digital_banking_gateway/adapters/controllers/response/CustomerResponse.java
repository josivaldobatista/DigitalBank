package com.jfb.digital_banking_gateway.adapters.controllers.response;

import com.jfb.digital_banking_gateway.core.domain.models.Status;

import java.time.LocalDate;

public record CustomerResponse(
        String name,
        String email,
        LocalDate birthDate,
        String cpfCnpj,
        Status status
) {}
