package com.jfb.digital_banking_data.dataprovider.account;

import com.jfb.digital_banking_data.core.dataprovider.account.FindAccount;
import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.dataprovider.repository.AccountRepository;
import com.jfb.digital_banking_data.dataprovider.repository.entity.AccountEntity;
import com.jfb.digital_banking_data.dataprovider.repository.mapper.AccountEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FindAccountImpl implements FindAccount {

    private final AccountRepository repository;
    private final AccountEntityMapper mapper;

    public FindAccountImpl(AccountRepository repository, AccountEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Account> findAll() {
        List<AccountEntity> accountsEntity = repository.findAll();
        return accountsEntity.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findById(String id) {
        Optional<AccountEntity> accountEntity = repository.findById(id);
        return accountEntity.map(mapper::toDomain);
    }

    @Override
    public Optional<Account> findByCpfCnpj(String cpfCnpj) {
        Optional<AccountEntity> accountEntity = repository.findByCpfCnpj(cpfCnpj);
        return accountEntity.map(mapper::toDomain);
    }
}
