package com.jfb.digital_banking_data.core.usecase.account.impl;

import com.jfb.digital_banking_data.core.dataprovider.account.FindAccount;
import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.core.usecase.account.FindAccountByIdUseCase;
import org.springframework.stereotype.Component;

@Component
public class FindAccountByIdUseCaseImpl implements FindAccountByIdUseCase {

    private final FindAccount findAccount;

    public FindAccountByIdUseCaseImpl(FindAccount findAccount) {
        this.findAccount = findAccount;
    }

    @Override
    public Account execute(String id) {
        return findAccount.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Account with ID " + id + " not found"));
    }
}
