package com.jfb.digital_banking_gateway.adapter.dto;

import com.jfb.digital_banking_gateway.domain.model.Status;

public record CustomerDTO(
        String name,
        String email,
        String birthDate,
        String cpfCnpj,
        Status status
) {
}
