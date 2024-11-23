package com.jfb.digital_banking_data.core.usecase.account.impl;

import com.jfb.digital_banking_data.core.dataprovider.account.FindAccount;
import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.core.usecase.account.FindAllAccountUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllAccountUseCaseImpl implements FindAllAccountUseCase {

    private final FindAccount findAccount;

    public FindAllAccountUseCaseImpl(FindAccount findAccount) {
        this.findAccount = findAccount;
    }

    @Override
    public List<Account> execute() {
        return findAccount.findAll();
    }
}
