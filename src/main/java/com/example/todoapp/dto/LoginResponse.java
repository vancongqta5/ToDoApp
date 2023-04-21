package com.example.todoapp.dto;

import lombok.Data;
import lombok.Getter;

// Lombok annotation to generate getters, setters, and other methods automatically
@Data
@Getter
public class LoginResponse {
    // The type of token (Bearer for JWT)
    private String tokenType = "Bearer";
    // The JWT access token
    private String accessToken;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}