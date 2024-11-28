package com.jfb.digital_banking_data.entrypoint.controller.response;

import com.jfb.digital_banking_data.core.domain.Status;

import java.time.LocalDate;

public record CustomerResponse(
        String name,
        String email,
        LocalDate birthDate,
        String cpfCnpj,
        Status status
) {
}
