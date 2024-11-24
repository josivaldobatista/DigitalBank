package com.jfb.digital_banking_data.core.dataprovider.operations;

import com.jfb.digital_banking_data.core.domain.BankTransaction;

public interface TransactionService {

    void recordTransaction(BankTransaction bankTransaction);
}
