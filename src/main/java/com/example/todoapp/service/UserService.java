package com.example.todoapp.service;

import com.example.todoapp.dto.requestDto.UserRequestDto;
import com.example.todoapp.dto.responseDto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRequestDto userRequestDto);
    boolean userNameIsExist(String userName);
    boolean emailIsExist(String userName);

}