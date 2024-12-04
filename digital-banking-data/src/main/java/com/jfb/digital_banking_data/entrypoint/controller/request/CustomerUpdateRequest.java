package com.jfb.digital_banking_data.entrypoint.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerUpdateRequest(
        @NotBlank(message = "Nome é obrigatório.")
        String name,

        @Email(message = "Email deve ter formato válido")
        @NotBlank(message = "Email é obrigatório.")
        String email
) {
}

