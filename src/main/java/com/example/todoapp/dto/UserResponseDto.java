package com.example.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponseDto {
    private Long id;
    private String username;
    //ẩn password
    @JsonIgnore
    private String password;
    private String email;
    private String rolenames;
    private Boolean locked;

    public UserResponseDto(Long id, String username, String email, Boolean locked) {
    }
}
