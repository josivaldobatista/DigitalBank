package com.jfb.digital_banking_data.core.dataprovider.account;

import com.jfb.digital_banking_data.core.domain.Account;

import java.util.List;
import java.util.Optional;

public interface FindAccount {
    List<Account> findAll();
    Optional<Account> findById(final String id);
    Optional<Account> findByCpfCnpj(String cpfCnpj);
}
