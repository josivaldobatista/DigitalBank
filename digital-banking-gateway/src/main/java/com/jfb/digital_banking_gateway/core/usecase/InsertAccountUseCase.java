package com.jfb.digital_banking_gateway.core.usecase;

import com.jfb.digital_banking_gateway.core.domain.models.Account;

public interface InsertAccountUseCase {

    void execute(Account model);
}
