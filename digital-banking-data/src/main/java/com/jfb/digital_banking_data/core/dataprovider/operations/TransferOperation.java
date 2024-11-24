package com.jfb.digital_banking_data.core.dataprovider.operations;

import java.math.BigDecimal;

public interface TransferOperation {

    void transfer(String fromAccountId, String toAccountId, BigDecimal amount);
}

