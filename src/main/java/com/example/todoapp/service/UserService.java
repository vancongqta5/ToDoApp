package com.example.todoapp.service;

import com.example.todoapp.dto.UserRequestDto;
import com.example.todoapp.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRequestDto userRequestDto);
}