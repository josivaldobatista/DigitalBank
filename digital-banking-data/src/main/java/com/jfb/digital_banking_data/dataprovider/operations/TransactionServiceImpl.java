package com.jfb.digital_banking_data.dataprovider.operations;

import com.jfb.digital_banking_data.core.dataprovider.operations.TransactionService;
import com.jfb.digital_banking_data.core.domain.BankTransaction;
import com.jfb.digital_banking_data.core.domain.TransactionType;
import com.jfb.digital_banking_data.dataprovider.repository.TransactionRepository;
import com.jfb.digital_banking_data.dataprovider.repository.mapper.BankTransactionMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final BankTransactionMapper mapper;

    public TransactionServiceImpl(TransactionRepository repository, BankTransactionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void recordTransaction(String accountId, BigDecimal amount, TransactionType transactionType) {
        BankTransaction transaction = new BankTransaction(
                UUID.randomUUID().toString(),
                accountId,
                null,
                amount,
                LocalDateTime.now(),
                transactionType
        );
        repository.save(mapper.toEntity(transaction));
    }

    @Override
    public void recordTransaction(BankTransaction bankTransaction) {
        repository.save(mapper.toEntity(bankTransaction));
    }
}
