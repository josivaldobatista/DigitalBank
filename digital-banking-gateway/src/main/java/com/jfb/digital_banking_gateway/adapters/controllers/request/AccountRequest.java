package com.jfb.digital_banking_gateway.adapters.controllers.request;

import com.jfb.digital_banking_gateway.core.domain.models.Status;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record AccountRequest(

        @NotBlank(message = "Account number is mandatory")
        String accountNumber,

        @NotBlank(message = "Branch is mandatory")
        String branch,

        @NotNull(message = "Balance is mandatory")
        @DecimalMin(value = "0.0", inclusive = false, message = "Balance must be greater than zero")
        BigDecimal balance,

        @NotBlank(message = "Customer ID is mandatory")
        String customerId,

        @NotNull(message = "Status is mandatory")
        Status status,

        @NotBlank(message = "CPF/CNPJ is mandatory")
        @Pattern(regexp = "^(\\d{11}|\\d{14})$", message = "CPF/CNPJ must be valid with 11 or 14 digits")
        String cpfCnpj
) {
}
