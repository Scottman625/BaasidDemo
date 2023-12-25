package com.example.demo.entity;

public class LoginResponse {
    private String account;
    private String name;

    public LoginResponse(String account, String name) {
        this.account = account;
        this.name = name;
    }

    // Getters

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
