package com.jfb.digital_banking_gateway.adapters.controllers.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerUpdateRequest(
        @NotBlank(message = "Nome é obrigatório.")
        String name,

        @Email(message = "Email deve ter formato válido")
        @NotBlank(message = "Email é obrigatório.")
        String email
) {
}

