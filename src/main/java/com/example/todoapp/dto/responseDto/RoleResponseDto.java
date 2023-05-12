package com.example.todoapp.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleResponseDto {
    private Long id;
    private String roleName;
    private List<String> userNames;
}
