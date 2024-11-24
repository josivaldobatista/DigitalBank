package com.jfb.digital_banking_data.dataprovider.operations;

import com.jfb.digital_banking_data.core.dataprovider.operations.DepositOperation;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.dataprovider.repository.AccountRepository;
import com.jfb.digital_banking_data.dataprovider.repository.entity.AccountEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DepositOperationImpl implements DepositOperation {

    private final AccountRepository repository;

    public DepositOperationImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deposit(String accountId, BigDecimal amount) {
        AccountEntity accountEntity = repository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + accountId));

        accountEntity.setBalance(accountEntity.getBalance().add(amount));
        repository.save(accountEntity);
    }
}
