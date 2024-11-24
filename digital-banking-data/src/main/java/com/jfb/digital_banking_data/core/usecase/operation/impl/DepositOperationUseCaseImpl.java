package com.jfb.digital_banking_data.core.usecase.operation.impl;

import com.jfb.digital_banking_data.core.dataprovider.operations.DepositOperation;
import com.jfb.digital_banking_data.core.dataprovider.operations.TransactionService;
import com.jfb.digital_banking_data.core.domain.BankTransaction;
import com.jfb.digital_banking_data.core.domain.TransactionType;
import com.jfb.digital_banking_data.core.usecase.operation.DepositOperationUseCase;
import com.jfb.digital_banking_data.dataprovider.repository.TransactionRepository;
import com.jfb.digital_banking_data.dataprovider.repository.mapper.BankTransactionMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DepositOperationUseCaseImpl implements DepositOperationUseCase {

    private final DepositOperation depositOperation;
    private final TransactionService transactionService;
    private final BankTransactionMapper mapper;

    public DepositOperationUseCaseImpl(DepositOperation depositOperation, TransactionService transactionService, BankTransactionMapper mapper) {
        this.depositOperation = depositOperation;
        this.transactionService = transactionService;
        this.mapper = mapper;
    }

    @Override
    public void execute(String accountId, BigDecimal amount) {
        depositOperation.deposit(accountId, amount);
        recordTransaction(accountId, amount);
    }

    private void recordTransaction(String accountId, BigDecimal amount) {
        BankTransaction transaction = new BankTransaction(
                UUID.randomUUID().toString(),
                accountId,
                null,
                amount,
                LocalDateTime.now(),
                TransactionType.DEPOSIT
        );
        transactionService.recordTransaction(transaction);
    }
}
