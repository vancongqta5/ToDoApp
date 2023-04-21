package com.example.todoapp.dto;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {
    @NotBlank()
    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters")
    private String username;
    @NotNull(message = "email can't be null")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank()
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters")
    private String password;
}
