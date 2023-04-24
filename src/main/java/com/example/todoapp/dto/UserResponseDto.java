package com.example.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;


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

    public UserResponseDto(Long id, String username, String email, String rolenames, Boolean locked) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.rolenames = rolenames;
        this.locked = locked;
    }
}
