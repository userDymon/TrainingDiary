package com.example.trainingdiary.payload.response;

public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // Геттер
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}