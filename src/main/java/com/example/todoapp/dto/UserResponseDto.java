package com.example.todoapp.dto;

import lombok.*;

@Data
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
    private Boolean locked;

    public UserResponseDto(Long id, String username, String email, Boolean locked) {
    }
}
