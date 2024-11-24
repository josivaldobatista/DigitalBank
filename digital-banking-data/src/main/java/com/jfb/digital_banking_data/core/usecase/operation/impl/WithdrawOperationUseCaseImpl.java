package com.jfb.digital_banking_data.core.usecase.operation.impl;

import com.jfb.digital_banking_data.core.dataprovider.operations.TransactionService;
import com.jfb.digital_banking_data.core.dataprovider.operations.WithdrawOperation;
import com.jfb.digital_banking_data.core.domain.TransactionType;
import com.jfb.digital_banking_data.core.usecase.operation.WithdrawOperationUseCase;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WithdrawOperationUseCaseImpl implements WithdrawOperationUseCase {

    private final WithdrawOperation withdrawOperation;
    private final TransactionService transactionService;

    public WithdrawOperationUseCaseImpl(WithdrawOperation withdrawOperation, TransactionService transactionService) {
        this.withdrawOperation = withdrawOperation;
        this.transactionService = transactionService;
    }

    @Override
    public void execute(String accountId, BigDecimal amount) {
        withdrawOperation.withdraw(accountId, amount);
        transactionService.recordTransaction(accountId, amount, TransactionType.WITHDRAW);
    }
}
