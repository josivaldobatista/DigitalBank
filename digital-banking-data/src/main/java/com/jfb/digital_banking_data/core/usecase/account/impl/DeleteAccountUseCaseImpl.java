package com.jfb.digital_banking_data.core.usecase.account.impl;

import com.jfb.digital_banking_data.core.dataprovider.account.DeleteAccount;
import com.jfb.digital_banking_data.core.usecase.account.DeleteAccountByIdUseCase;
import org.springframework.stereotype.Component;

@Component
public class DeleteAccountUseCaseImpl implements DeleteAccountByIdUseCase {

    private final DeleteAccount deleteAccount;

    public DeleteAccountUseCaseImpl(DeleteAccount insertAccount) {
        this.deleteAccount = insertAccount;
    }

    @Override
    public void execute(String id) {
        deleteAccount.logicalDelete(id);
    }
}
