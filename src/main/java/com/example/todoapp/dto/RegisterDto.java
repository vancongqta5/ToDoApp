package com.example.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {
    @JsonPropertyOrder({ "username", "email", "password" })
    @NotNull(message = "username can't be null")
    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters")
    private String username;
    @NotNull(message = "email can't be null")
    @Email(message = "Invalid email address")
    private String email;
    @NotNull(message = "password can't be null")
    private String password;
}
