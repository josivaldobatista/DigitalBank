package com.jfb.digital_banking_data.entrypoint.controller.response;

import com.jfb.digital_banking_data.core.domain.Status;

import java.time.LocalDate;

public record CustomerResponse(
        String name,
        String email,
        String username,
        LocalDate birthDate,
        String cpfCnpj,
        Status status
) {
}
