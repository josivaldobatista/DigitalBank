package com.jfb.digital_banking_gateway.adapters.controllers;

import com.jfb.digital_banking_gateway.adapters.controllers.mapper.AccountMapper;
import com.jfb.digital_banking_gateway.adapters.controllers.request.AccountRequest;
import com.jfb.digital_banking_gateway.core.usecase.account.DeleteAccountUseCase;
import com.jfb.digital_banking_gateway.core.usecase.account.InsertAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountMapper mapper;
    private final InsertAccountUseCase insertAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;

    public AccountController(AccountMapper mapper, InsertAccountUseCase useCase, DeleteAccountUseCase deleteAccountUseCase) {
        this.mapper = mapper;
        this.insertAccountUseCase = useCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insert(@RequestBody AccountRequest request) {
        var model = mapper.toModel(request);
        insertAccountUseCase.execute(model);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        deleteAccountUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
