package com.jfb.digital_banking_gateway.domain.model;

public class Customer {

    private String name;
    private String email;
    private String birthDate;
    private String cpfCnpj;
    private Status status;

    public Customer(String name, String email, String birthDate, String cpfCnpj, Status status) {
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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
}

