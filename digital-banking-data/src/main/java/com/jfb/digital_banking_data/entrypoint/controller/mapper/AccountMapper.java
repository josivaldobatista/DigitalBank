package com.jfb.digital_banking_data.entrypoint.controller.mapper;

import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.entrypoint.controller.request.AccountRequest;
import com.jfb.digital_banking_data.entrypoint.controller.response.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toAccount(AccountRequest request) {
        var account = new Account();
        account.setId(request.id());
        account.setCustomerId(request.customerId());
        account.setAccountNumber(request.accountNumber());
        account.setBalance(request.balance());
        account.setBranch(request.branch());
        account.setStatus(request.status());
        account.setCpfCnpj(request.cpfCnpj());
        return account;
    }

    public AccountResponse toAccountResponse(Account account) {
        return new AccountResponse(
                account.getCustomerId(),
                account.getAccountNumber(),
                account.getBranch(),
                account.getCpfCnpj(),
                account.getBalance(),
                account.getStatus()
        );
    }
}