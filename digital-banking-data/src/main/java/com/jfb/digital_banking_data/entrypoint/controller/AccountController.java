package com.jfb.digital_banking_data.entrypoint.controller;

import com.jfb.digital_banking_data.core.dataprovider.account.InsertAccount;
import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.core.usecase.account.DeleteAccountByIdUseCase;
import com.jfb.digital_banking_data.core.usecase.account.FindAllAccountUseCase;
import com.jfb.digital_banking_data.core.usecase.account.impl.FindAccountByIdUseCaseImpl;
import com.jfb.digital_banking_data.entrypoint.controller.mapper.AccountMapper;
import com.jfb.digital_banking_data.entrypoint.controller.request.AccountRequest;
import com.jfb.digital_banking_data.entrypoint.controller.response.AccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountMapper mapper;
    private final InsertAccount insertAccount;
    private final FindAllAccountUseCase findAllAccountUseCase;
    private final FindAccountByIdUseCaseImpl findAccountByIdUseCase;
    private final DeleteAccountByIdUseCase deleteAccountByIdUseCase;

    public AccountController(
            AccountMapper mapper,
            InsertAccount insertAccount,
            FindAllAccountUseCase findAllAccountUseCase,
            FindAccountByIdUseCaseImpl findAccountByIdUseCase, DeleteAccountByIdUseCase deleteAccountByIdUseCase) {
        this.mapper = mapper;
        this.insertAccount = insertAccount;
        this.findAllAccountUseCase = findAllAccountUseCase;
        this.findAccountByIdUseCase = findAccountByIdUseCase;
        this.deleteAccountByIdUseCase = deleteAccountByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insert(@RequestBody AccountRequest request) {
        var account = mapper.toAccount(request);
        insertAccount.insert(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll() {
        List<Account> accounts = findAllAccountUseCase.execute();
        List<AccountResponse> accountsResponse = accounts.stream()
                .map(mapper::toAccountResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(accountsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable("id") final String id) {
        Account account = findAccountByIdUseCase.execute(id);
        AccountResponse response = mapper.toAccountResponse(account);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> logicalDelete(@PathVariable("id") String id) {
        deleteAccountByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
