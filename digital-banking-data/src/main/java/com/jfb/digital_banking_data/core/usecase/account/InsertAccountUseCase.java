package com.jfb.digital_banking_data.core.usecase.account;

import com.jfb.digital_banking_data.core.domain.Account;

public interface InsertAccountUseCase {

    void execute(Account account);
}
