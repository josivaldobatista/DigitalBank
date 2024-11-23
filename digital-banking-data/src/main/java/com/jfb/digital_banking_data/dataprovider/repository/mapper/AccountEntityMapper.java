package com.jfb.digital_banking_data.dataprovider.repository.mapper;

import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.dataprovider.repository.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityMapper {

    public AccountEntity toEntity(Account account) {
        var entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setCustomerId(account.getCustomerId());
        entity.setBranch(account.getBranch());
        entity.setStatus(account.getStatus());
        entity.setAccountNumber(account.getAccountNumber());
        entity.setBalance(account.getBalance());
        entity.setCpfCnpj(account.getCpfCnpj());
        return entity;
    }

    public Account toAccount(AccountEntity entity) {
        var account = new Account();
        account.setCustomerId(entity.getCustomerId());
        account.setAccountNumber(entity.getAccountNumber());
        account.setCpfCnpj(entity.getCpfCnpj());
        account.setBranch(entity.getBranch());
        account.setStatus(entity.getStatus());
        account.setBalance(entity.getBalance());
        return account;
    }
}
