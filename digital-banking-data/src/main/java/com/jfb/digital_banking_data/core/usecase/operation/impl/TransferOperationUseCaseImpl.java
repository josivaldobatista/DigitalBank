package com.jfb.digital_banking_data.core.usecase.operation.impl;

import com.jfb.digital_banking_data.core.dataprovider.operations.TransactionService;
import com.jfb.digital_banking_data.core.dataprovider.operations.TransferOperation;
import com.jfb.digital_banking_data.core.domain.BankTransaction;
import com.jfb.digital_banking_data.core.domain.TransactionType;
import com.jfb.digital_banking_data.core.usecase.operation.TransferOperationUseCase;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransferOperationUseCaseImpl implements TransferOperationUseCase {

    private final TransferOperation transferOperation;
    private final TransactionService transactionService;

    public TransferOperationUseCaseImpl(TransferOperation transferOperation, TransactionService transactionService) {
        this.transferOperation = transferOperation;
        this.transactionService = transactionService;
    }

    @Override
    public void execute(String fromAccountId, String toAccountId, BigDecimal amount) {
        transferOperation.transfer(fromAccountId, toAccountId, amount);
        recordTransaction(fromAccountId, toAccountId, amount);
    }

    private void recordTransaction(String fromAccountId, String toAccountId, BigDecimal amount) {
        var bankTransaction = new BankTransaction(
                UUID.randomUUID().toString(),
                fromAccountId,
                toAccountId,
                amount,
                LocalDateTime.now(),
                TransactionType.TRANSFER
        );
        transactionService.recordTransaction(bankTransaction);
    }
}
