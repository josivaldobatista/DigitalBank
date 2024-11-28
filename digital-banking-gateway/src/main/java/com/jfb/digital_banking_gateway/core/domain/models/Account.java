package com.jfb.digital_banking_gateway.core.domain.models;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String accountNumber;
    private String branch;
    private BigDecimal balance;
    private String customerId;
    private Status status;
    private String cpfCnpj;

    public Account(String accountNumber, String branch, BigDecimal balance, String customerId, Status status, String cpfCnpj) {
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.balance = balance;
        this.customerId = customerId;
        this.status = status;
        this.cpfCnpj = cpfCnpj;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber) && Objects.equals(branch, account.branch) && Objects.equals(balance, account.balance) && Objects.equals(customerId, account.customerId) && status == account.status && Objects.equals(cpfCnpj, account.cpfCnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, branch, balance, customerId, status, cpfCnpj);
    }
}

