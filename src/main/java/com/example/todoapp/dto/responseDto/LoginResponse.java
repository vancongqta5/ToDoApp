package com.example.todoapp.dto.responseDto;

import lombok.Data;
import lombok.Getter;

// Lombok annotation to generate getters, setters, and other methods automatically
@Getter
@Data
public class LoginResponse {
    private UserResponseDto userResponseDto;
    private String tokenType = "Bearer";
    private String accessToken;

    public LoginResponse(UserResponseDto userResponseDto, String accessToken) {
        this.userResponseDto = userResponseDto;
        this.accessToken = accessToken;
    }
}