package com.jfb.digital_banking_data.entrypoint.controller.response;

import com.jfb.digital_banking_data.core.domain.Status;

import java.math.BigDecimal;

public record AccountResponse(
        String customerId,
        String accountNumber,
        String branch,
        String cpfCnpj,
        BigDecimal balance,
        Status status
) {
}
