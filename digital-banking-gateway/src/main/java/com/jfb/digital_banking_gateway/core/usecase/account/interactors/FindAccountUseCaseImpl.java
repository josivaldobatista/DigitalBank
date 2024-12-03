package com.jfb.digital_banking_gateway.core.usecase.account.interactors;

import com.jfb.digital_banking_gateway.core.domain.models.Account;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.usecase.account.FindAccountUseCase;
import com.jfb.digital_banking_gateway.core.usecase.exceptions.ResourceNotFoundException;
import com.jfb.digital_banking_gateway.dataprovider.client.AccountClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindAccountUseCaseImpl implements FindAccountUseCase {

    private final AccountClient accountClient;

    public FindAccountUseCaseImpl(AccountClient customerClient) {
        this.accountClient = customerClient;
    }

    @Override
    public Optional<Account> findById(String id) {
        return Optional.ofNullable(accountClient.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id)));
    }

    @Override
    public List<Account> findAllCustomers() {
        return accountClient.findAll();
    }
}
