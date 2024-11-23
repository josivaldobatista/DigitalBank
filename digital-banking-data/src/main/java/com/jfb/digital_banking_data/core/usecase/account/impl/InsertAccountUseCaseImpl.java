package com.jfb.digital_banking_data.core.usecase.account.impl;

import com.jfb.digital_banking_data.core.dataprovider.account.InsertAccount;
import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.core.usecase.account.InsertAccountUseCase;
import org.springframework.stereotype.Component;

@Component
public class InsertAccountUseCaseImpl implements InsertAccountUseCase {

    private final InsertAccount insertAccount;

    public InsertAccountUseCaseImpl(InsertAccount insertAccount) {
        this.insertAccount = insertAccount;
    }

    @Override
    public void execute(Account account) {
        insertAccount.insert(account);
    }
}
