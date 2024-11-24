package com.jfb.digital_banking_data.entrypoint.controller;

import com.jfb.digital_banking_data.core.usecase.operation.DepositOperationUseCase;
import com.jfb.digital_banking_data.entrypoint.controller.request.DepositRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/transactions")
public class BankTransactionController {

    private final DepositOperationUseCase depositOperationUseCase;

    public BankTransactionController(DepositOperationUseCase depositOperationUseCase) {
        this.depositOperationUseCase = depositOperationUseCase;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody DepositRequest request) {
        depositOperationUseCase.execute(request.accountId(), request.amount());
        return ResponseEntity.ok().build();
    }
}

