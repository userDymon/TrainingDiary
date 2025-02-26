package com.example.trainingdiary.payload.request;

public class SignupRequest {
    private String username;
    private String password;
    private String email;

    // Геттери та сеттери
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}