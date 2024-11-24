package com.jfb.digital_banking_data.core.dataprovider.operations;

import java.math.BigDecimal;

public interface DepositOperation {

    void deposit (String accountId, BigDecimal amount);
}
