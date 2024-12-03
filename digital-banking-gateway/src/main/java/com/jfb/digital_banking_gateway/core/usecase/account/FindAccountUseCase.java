package com.jfb.digital_banking_gateway.core.usecase.account;

import com.jfb.digital_banking_gateway.core.domain.models.Account;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;

import java.util.List;
import java.util.Optional;

public interface FindAccountUseCase {

    Optional<Account> findById(String id);
    List<Account> findAllCustomers();
}
