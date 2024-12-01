package com.jfb.digital_banking_gateway.core.usecase.account;

import com.jfb.digital_banking_gateway.core.domain.models.Account;

public interface InsertAccountUseCase {

    void execute(Account model);
}
