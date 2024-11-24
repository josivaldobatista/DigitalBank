package com.jfb.digital_banking_data.core.dataprovider.operations;

import com.jfb.digital_banking_data.core.domain.BankTransaction;
import com.jfb.digital_banking_data.core.domain.TransactionType;

import java.math.BigDecimal;

public interface TransactionService {

    void recordTransaction(String accountId, BigDecimal amount, TransactionType transactionType);
    void recordTransaction(BankTransaction bankTransaction);
}
