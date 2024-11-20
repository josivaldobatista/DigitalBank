package com.jfb.digital_banking_data.entrypoint.controller.request;

import com.jfb.digital_banking_data.core.domain.Status;

public record CustomerRequest(
        String name,
        String email,
        String birthDate,
        String cpfCnpj,
        Status status
) {
}
