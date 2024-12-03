package com.jfb.digital_banking_gateway.adapters.controllers.mapper;

import com.jfb.digital_banking_gateway.adapters.controllers.request.AccountRequest;
import com.jfb.digital_banking_gateway.adapters.controllers.response.AccountResponse;
import com.jfb.digital_banking_gateway.core.domain.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toModel(AccountRequest request) {
        var model = new Account();
        model.setAccountNumber(request.accountNumber());
        model.setBranch(request.branch());
        model.setBalance(request.balance());
        model.setCustomerId(request.customerId());
        model.setStatus(request.status());
        model.setCpfCnpj(request.cpfCnpj());
        return model;
    }

    public AccountResponse toResponse(Account model) {
        return new AccountResponse(
             model.getAccountNumber(),
             model.getBranch(),
             model.getBalance(),
             model.getCustomerId(),
             model.getStatus(),
             model.getCpfCnpj()
        );
    }
}
