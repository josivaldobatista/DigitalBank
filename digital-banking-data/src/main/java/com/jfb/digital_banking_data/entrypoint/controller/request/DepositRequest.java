package com.jfb.digital_banking_data.entrypoint.controller.request;

import java.math.BigDecimal;

public record DepositRequest(
        String accountId,
        BigDecimal amount
) {
}

