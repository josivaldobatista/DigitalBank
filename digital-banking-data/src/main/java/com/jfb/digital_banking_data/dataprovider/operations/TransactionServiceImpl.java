package com.jfb.digital_banking_data.dataprovider.operations;

import com.jfb.digital_banking_data.core.dataprovider.operations.TransactionService;
import com.jfb.digital_banking_data.core.domain.BankTransaction;
import com.jfb.digital_banking_data.dataprovider.repository.TransactionRepository;
import com.jfb.digital_banking_data.dataprovider.repository.mapper.BankTransactionMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final BankTransactionMapper mapper;

    public TransactionServiceImpl(TransactionRepository repository, BankTransactionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void recordTransaction(BankTransaction bankTransaction) {
        repository.save(mapper.toEntity(bankTransaction));
    }
}
