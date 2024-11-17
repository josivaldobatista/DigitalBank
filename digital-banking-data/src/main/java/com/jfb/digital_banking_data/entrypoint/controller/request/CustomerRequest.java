package com.jfb.digital_banking_data.entrypoint.controller.request;

public record CustomerRequest(
        String name,
        String email,
        String birthDate,
        String cpfCnpj
) {
}
