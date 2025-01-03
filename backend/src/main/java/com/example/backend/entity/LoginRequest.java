package com.example.backend.entity;

public class LoginRequest {
    private String userEmail;
    private String userPassword;

    // GETs
    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    // SETs
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}