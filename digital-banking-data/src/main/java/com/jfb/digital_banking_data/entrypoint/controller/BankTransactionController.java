package com.jfb.digital_banking_data.entrypoint.controller;

import com.jfb.digital_banking_data.core.usecase.operation.DepositOperationUseCase;
import com.jfb.digital_banking_data.core.usecase.operation.TransferOperationUseCase;
import com.jfb.digital_banking_data.core.usecase.operation.WithdrawOperationUseCase;
import com.jfb.digital_banking_data.entrypoint.controller.request.DepositRequest;
import com.jfb.digital_banking_data.entrypoint.controller.request.TransferRequest;
import com.jfb.digital_banking_data.entrypoint.controller.request.WithdrawRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
public class BankTransactionController {

    private final DepositOperationUseCase depositOperationUseCase;
    private final WithdrawOperationUseCase withdrawOperationUseCase;
    private final TransferOperationUseCase transferOperationUseCase;

    public BankTransactionController(DepositOperationUseCase depositOperationUseCase, WithdrawOperationUseCase withdrawOperationUseCase, TransferOperationUseCase transferOperationUseCase) {
        this.depositOperationUseCase = depositOperationUseCase;
        this.withdrawOperationUseCase = withdrawOperationUseCase;
        this.transferOperationUseCase = transferOperationUseCase;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody DepositRequest request) {
        depositOperationUseCase.execute(request.accountId(), request.amount());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody WithdrawRequest request) {
        withdrawOperationUseCase.execute(request.accountId(), request.amount());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest request) {
        transferOperationUseCase.execute(request.fromAccountId(), request.toAccountId(), request.amount());
        return ResponseEntity.ok().build();
    }
}

