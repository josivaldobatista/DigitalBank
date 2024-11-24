package com.jfb.digital_banking_data.entrypoint.controller;

import com.jfb.digital_banking_data.core.usecase.operation.DepositOperationUseCase;
import com.jfb.digital_banking_data.core.usecase.operation.WithdrawOperationUseCase;
import com.jfb.digital_banking_data.entrypoint.controller.request.DepositRequest;
import com.jfb.digital_banking_data.entrypoint.controller.request.WithDrawRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/transactions")
public class BankTransactionController {

    private final DepositOperationUseCase depositOperationUseCase;
    private final WithdrawOperationUseCase withdrawOperationUseCase;

    public BankTransactionController(DepositOperationUseCase depositOperationUseCase, WithdrawOperationUseCase withdrawOperationUseCase) {
        this.depositOperationUseCase = depositOperationUseCase;
        this.withdrawOperationUseCase = withdrawOperationUseCase;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody DepositRequest request) {
        depositOperationUseCase.execute(request.accountId(), request.amount());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody WithDrawRequest request) {
        withdrawOperationUseCase.execute(request.accountId(), request.amount());
        return ResponseEntity.ok().build();
    }
}

