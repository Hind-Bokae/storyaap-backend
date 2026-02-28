package com.example.storyapp.dto;

public class AuthResponse {

    private String token;

    public AuthResponse() {}

    public AuthResponse(String message) {
        this.token = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
