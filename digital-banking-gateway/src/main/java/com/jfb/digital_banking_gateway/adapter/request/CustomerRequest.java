package com.jfb.digital_banking_gateway.adapter.request;

import com.jfb.digital_banking_gateway.domain.model.Status;

public record CustomerRequest(
        String name,
        String email,
        String birthDate,
        String cpfCnpj,
        Status status
) {
}
