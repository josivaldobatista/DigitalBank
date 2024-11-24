package com.jfb.digital_banking_data.core.dataprovider.operations;

import java.math.BigDecimal;

public interface WithdrawOperation {

    void withdraw(String accountId, BigDecimal amount);
}
