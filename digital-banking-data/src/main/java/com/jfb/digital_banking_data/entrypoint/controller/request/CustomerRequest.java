package com.jfb.digital_banking_data.entrypoint.controller.request;

import com.jfb.digital_banking_data.core.domain.Status;

import java.time.LocalDate;

public record CustomerRequest(
        String name,
        String email,
        LocalDate birthDate,
        String cpfCnpj,
        Status status
) {
}
