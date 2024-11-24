package com.jfb.digital_banking_data.core.usecase.operation;

import java.math.BigDecimal;

public interface WithdrawOperationUseCase {

    void execute(String accountId, BigDecimal amount);
}
