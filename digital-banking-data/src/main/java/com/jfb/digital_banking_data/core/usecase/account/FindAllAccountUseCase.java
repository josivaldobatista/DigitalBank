package com.jfb.digital_banking_data.core.usecase.account;

import com.jfb.digital_banking_data.core.domain.Account;

import java.util.List;

public interface FindAllAccountUseCase {

    List<Account> execute();
}
