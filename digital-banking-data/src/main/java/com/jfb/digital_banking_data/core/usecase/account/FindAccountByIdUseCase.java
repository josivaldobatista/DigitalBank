package com.jfb.digital_banking_data.core.usecase.account;

import com.jfb.digital_banking_data.core.domain.Account;

public interface FindAccountByIdUseCase {

    Account execute(String id);
}
