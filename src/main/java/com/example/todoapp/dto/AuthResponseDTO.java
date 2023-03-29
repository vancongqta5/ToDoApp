package com.example.todoapp.dto;

import lombok.Data;

// Lombok annotation to generate getters, setters, and other methods automatically
@Data
public class AuthResponseDTO {
    // The JWT access token
    private String accessToken;
    // The type of token (Bearer for JWT)
    private String tokenType = "Bearer";

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}