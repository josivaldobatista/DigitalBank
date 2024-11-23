package com.jfb.digital_banking_data.entrypoint.controller;

import com.jfb.digital_banking_data.core.dataprovider.account.InsertAccount;
import com.jfb.digital_banking_data.entrypoint.controller.mapper.AccountMapper;
import com.jfb.digital_banking_data.entrypoint.controller.request.AccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountMapper mapper;
    private final InsertAccount insertAccount;

    public AccountController(AccountMapper mapper, InsertAccount insertAccount) {
        this.mapper = mapper;
        this.insertAccount = insertAccount;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insert(@RequestBody AccountRequest request) {
        var account = mapper.toAccount(request);
        insertAccount.insert(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
