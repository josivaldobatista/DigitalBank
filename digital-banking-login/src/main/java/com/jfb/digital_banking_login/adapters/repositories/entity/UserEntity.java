package com.jfb.digital_banking_login.adapters.repositories.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;
    private String username;
    private String password;
    private String cpfCnpj;
    private String email;
    private List<String> roles;

    public UserEntity() {
    }

    public UserEntity(String id, String username, String password, String cpfCnpj, String email, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.roles = roles;
    }

    public UserEntity(String username, String password, String cpfCnpj, String email, List<String> roles) {
        this.username = username;
        this.password = password;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
