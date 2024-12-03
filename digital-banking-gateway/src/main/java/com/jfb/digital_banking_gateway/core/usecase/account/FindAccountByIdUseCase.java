package com.jfb.digital_banking_gateway.core.usecase.account;

import com.jfb.digital_banking_gateway.core.domain.models.Account;

import java.util.Optional;

public interface FindAccountByIdUseCase {
    Optional<Account> findById(String id);
}
