package com.jfb.digital_banking_gateway.core.domain.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private LocalDate birthDate;
    private String cpfCnpj;
    private Status status;

    public Customer(String name, String email, LocalDate birthDate, String cpfCnpj, Status status) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.cpfCnpj = cpfCnpj;
        this.status = status;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(birthDate, customer.birthDate) && Objects.equals(cpfCnpj, customer.cpfCnpj) && status == customer.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, birthDate, cpfCnpj, status);
    }
}
