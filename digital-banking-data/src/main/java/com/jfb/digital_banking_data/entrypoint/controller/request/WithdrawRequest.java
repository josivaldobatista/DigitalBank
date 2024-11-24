package com.jfb.digital_banking_data.entrypoint.controller.request;

import java.math.BigDecimal;

public record WithdrawRequest(
        String accountId,
        BigDecimal amount
) {
}
