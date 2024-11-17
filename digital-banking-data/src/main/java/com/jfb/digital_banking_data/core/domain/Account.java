package com.jfb.digital_banking_data.core.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {

    private UUID id;
    private String accountNumber;
    private String branch;
    private BigDecimal balance;
    private UUID customerId;

    public Account(UUID id, String accountNumber, String branch, BigDecimal balance, UUID customerId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.balance = balance;
        this.customerId = customerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}

