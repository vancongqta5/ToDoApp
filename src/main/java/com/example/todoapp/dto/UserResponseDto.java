package com.example.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponseDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String rolenames;
    
}
