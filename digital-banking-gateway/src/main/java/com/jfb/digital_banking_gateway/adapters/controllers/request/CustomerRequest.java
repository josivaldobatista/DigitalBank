package com.jfb.digital_banking_gateway.adapters.controllers.request;

import com.jfb.digital_banking_gateway.core.domain.models.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerRequest(
        String id,
        @NotBlank(message = "Nome é obrigatório.")
        String name,

        @Email(message = "Email deve ter formato válido")
        @NotBlank(message = "Email é obrigatório.")
        String email,

        String username,
        String password,

        @NotNull(message = "Data de nascimento é obrigatória.")
        LocalDate birthDate,

        @NotBlank(message = "CPF ou CNPJ é obrigatório.")
        String cpfCnpj,

        @NotNull(message = "Status é obrigatório.")
        Status status

) {}

