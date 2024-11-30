package com.jfb.digital_banking_gateway.adapters.controllers;

import com.jfb.digital_banking_gateway.adapters.controllers.mapper.AccountMapper;
import com.jfb.digital_banking_gateway.adapters.controllers.request.AccountRequest;
import com.jfb.digital_banking_gateway.core.usecase.InsertAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountMapper mapper;
    private final InsertAccountUseCase insertAccountUseCase;

    public AccountController(AccountMapper mapper, InsertAccountUseCase useCase) {
        this.mapper = mapper;
        this.insertAccountUseCase = useCase;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insert(@RequestBody AccountRequest request) {
        var model = mapper.toModel(request);
        insertAccountUseCase.execute(model);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
