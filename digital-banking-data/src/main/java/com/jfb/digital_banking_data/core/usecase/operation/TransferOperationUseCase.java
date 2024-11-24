package com.jfb.digital_banking_data.core.usecase.operation;

import java.math.BigDecimal;

public interface TransferOperationUseCase {

    void execute(String fromAccountId, String toAccountId, BigDecimal amount);
}
