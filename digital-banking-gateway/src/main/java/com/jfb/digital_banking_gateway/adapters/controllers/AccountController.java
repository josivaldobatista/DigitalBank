package com.jfb.digital_banking_gateway.adapters.controllers;

import com.jfb.digital_banking_gateway.adapters.controllers.mapper.AccountMapper;
import com.jfb.digital_banking_gateway.adapters.controllers.request.AccountRequest;
import com.jfb.digital_banking_gateway.adapters.controllers.response.AccountResponse;
import com.jfb.digital_banking_gateway.adapters.controllers.response.CustomerResponse;
import com.jfb.digital_banking_gateway.core.domain.models.Account;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.usecase.account.DeleteAccountUseCase;
import com.jfb.digital_banking_gateway.core.usecase.account.FindAccountUseCase;
import com.jfb.digital_banking_gateway.core.usecase.account.InsertAccountUseCase;
import com.jfb.digital_banking_gateway.core.usecase.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountMapper mapper;
    private final InsertAccountUseCase insertAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final FindAccountUseCase findAccountByIdUseCase;

    public AccountController(AccountMapper mapper, InsertAccountUseCase useCase, DeleteAccountUseCase deleteAccountUseCase, FindAccountUseCase findAccountByIdUseCase) {
        this.mapper = mapper;
        this.insertAccountUseCase = useCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
        this.findAccountByIdUseCase = findAccountByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insert(@RequestBody @Valid AccountRequest request) {
        var model = mapper.toModel(request);
        insertAccountUseCase.execute(model);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        deleteAccountUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable("id") String id) {
        Account account = findAccountByIdUseCase.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        AccountResponse response = mapper.toResponse(account);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll() {
        List<Account> accounts = findAccountByIdUseCase.findAllCustomers();
        List<AccountResponse> response = accounts.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
