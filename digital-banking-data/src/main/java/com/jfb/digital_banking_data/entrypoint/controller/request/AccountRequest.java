package com.jfb.digital_banking_data.entrypoint.controller.request;

import com.jfb.digital_banking_data.core.domain.Status;

import java.math.BigDecimal;

public record AccountRequest(
        String id,
        String accountNumber,
        String branch,
        BigDecimal balance,
        String customerId,
        Status status,
        String cpfCnpj
) {
}
