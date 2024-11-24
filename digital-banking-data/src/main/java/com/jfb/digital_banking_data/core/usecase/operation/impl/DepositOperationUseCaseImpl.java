package com.jfb.digital_banking_data.core.usecase.operation.impl;

import com.jfb.digital_banking_data.core.dataprovider.operations.DepositOperation;
import com.jfb.digital_banking_data.core.dataprovider.operations.TransactionService;
import com.jfb.digital_banking_data.core.domain.TransactionType;
import com.jfb.digital_banking_data.core.usecase.operation.DepositOperationUseCase;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DepositOperationUseCaseImpl implements DepositOperationUseCase {

    private final DepositOperation depositOperation;
    private final TransactionService transactionService;

    public DepositOperationUseCaseImpl(DepositOperation depositOperation, TransactionService transactionService) {
        this.depositOperation = depositOperation;
        this.transactionService = transactionService;
    }

    @Override
    public void execute(String accountId, BigDecimal amount) {
        depositOperation.deposit(accountId, amount);
        transactionService.recordTransaction(accountId, amount, TransactionType.DEPOSIT);
    }
}
