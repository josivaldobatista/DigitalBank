package com.jfb.digital_banking_data.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankTransaction {

    private String id;
    private String sourceAccountId;
    private String destinationAccountId; // Opcional para depósitos
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private TransactionType transactionType; // "DEPOSIT", "TRANSFER"

    public BankTransaction(String id, String sourceAccountId, String destinationAccountId, BigDecimal amount, LocalDateTime transactionDate, TransactionType transactionType) {
        this.id = id;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
