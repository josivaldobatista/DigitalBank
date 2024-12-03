package com.jfb.digital_banking_gateway.adapters.controllers.response;

import com.jfb.digital_banking_gateway.core.domain.models.Status;

import java.math.BigDecimal;

public record AccountResponse(
        String accountNumber,
        String branch,
        BigDecimal balance,
        String customerId,
        Status status,
        String cpfCnpj
) {
}
