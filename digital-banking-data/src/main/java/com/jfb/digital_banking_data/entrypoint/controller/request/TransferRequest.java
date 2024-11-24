package com.jfb.digital_banking_data.entrypoint.controller.request;

import java.math.BigDecimal;

public record TransferRequest(
        String fromAccountId,
        String toAccountId,
        BigDecimal amount
) {
}
