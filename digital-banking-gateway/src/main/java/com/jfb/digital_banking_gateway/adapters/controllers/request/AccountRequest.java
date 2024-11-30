package com.jfb.digital_banking_gateway.adapters.controllers.request;

import com.jfb.digital_banking_gateway.core.domain.models.Status;

import java.math.BigDecimal;

public record AccountRequest(
        String accountNumber,
        String branch,
        BigDecimal balance,
        String customerId,
        Status status,
        String cpfCnpj
) {
}
