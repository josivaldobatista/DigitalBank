package com.jfb.digital_banking_data.dataprovider.operations;

import com.jfb.digital_banking_data.core.dataprovider.operations.WithdrawOperation;
import com.jfb.digital_banking_data.core.exception.InsufficientFundsException;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.dataprovider.repository.AccountRepository;
import com.jfb.digital_banking_data.dataprovider.repository.entity.AccountEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WithdrawOperationImpl implements WithdrawOperation {

    private final AccountRepository repository;

    public WithdrawOperationImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void withdraw(String accountId, BigDecimal amount) {
        AccountEntity accountEntity = repository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + accountId));

        if (accountEntity.getBalance().compareTo(amount) <= 0) {
            throw new InsufficientFundsException("Insufficient funds in account ID: " + accountId);
        }

        accountEntity.setBalance(accountEntity.getBalance().subtract(amount));
        repository.save(accountEntity);
    }
}
