package com.jfb.digital_banking_data.core.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class BankTransaction {

    private UUID id;
    private UUID sourceAccountId;
    private UUID destinationAccountId; // Opcional para depósitos
    private BigDecimal amount;
    private String transactionDate;
    private String transactionType; // "DEPOSIT", "TRANSFER"

    public BankTransaction(UUID id, UUID sourceAccountId, UUID destinationAccountId, BigDecimal amount, String transactionDate, String transactionType) {
        this.id = id;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(UUID sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public UUID getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(UUID destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
