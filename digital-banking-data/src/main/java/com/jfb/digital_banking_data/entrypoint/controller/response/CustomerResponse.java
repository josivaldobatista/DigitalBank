package com.jfb.digital_banking_data.entrypoint.controller.response;

public record CustomerResponse(
        String name,
        String email,
        String birthDate,
        String cpfCnpj
) {
}
