package com.jfb.digital_banking_gateway.core.domain.models;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class BankTransaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String sourceAccountId;
    private String destinationAccountId; // Opcional para depósitos
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private TransactionType transactionType;

    public BankTransaction(String sourceAccountId, String destinationAccountId, BigDecimal amount, LocalDateTime transactionDate, TransactionType transactionType) {
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BankTransaction that = (BankTransaction) o;
        return Objects.equals(sourceAccountId, that.sourceAccountId) && Objects.equals(destinationAccountId, that.destinationAccountId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate) && transactionType == that.transactionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceAccountId, destinationAccountId, amount, transactionDate, transactionType);
    }
}
