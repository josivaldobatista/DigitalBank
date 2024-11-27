package com.jfb.digital_banking_data.core.domain;

import java.math.BigDecimal;

public class Account {

    private String id;
    private String accountNumber;
    private String branch;
    private BigDecimal balance;
    private String customerId;
    private Status status;
    private String cpfCnpj;

    public Account() {
    }

    public Account(String id, String accountNumber, String branch, BigDecimal balance, String customerId, Status status, String cpfCnpj) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.balance = balance;
        this.customerId = customerId;
        this.status = status;
        this.cpfCnpj = cpfCnpj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
}

