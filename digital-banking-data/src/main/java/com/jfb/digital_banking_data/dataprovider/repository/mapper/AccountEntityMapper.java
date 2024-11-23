package com.jfb.digital_banking_data.dataprovider.repository.mapper;

import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.dataprovider.repository.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityMapper {

    public AccountEntity toEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setCustomer_id(account.getCustomerId());
        entity.setBranch(account.getBranch());
        entity.setStatus(account.getStatus());
        entity.setAccountNumber(account.getAccountNumber());
        entity.setBalance(account.getBalance());
        entity.setCpfCnpj(account.getCpfCnpj());
        return entity;
    }
}
