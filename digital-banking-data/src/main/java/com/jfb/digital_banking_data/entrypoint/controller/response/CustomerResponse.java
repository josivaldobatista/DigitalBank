package com.jfb.digital_banking_data.entrypoint.controller.response;

import com.jfb.digital_banking_data.core.domain.Status;

public record CustomerResponse(
        String name,
        String email,
        String birthDate,
        String cpfCnpj,
        Status status
) {
}
