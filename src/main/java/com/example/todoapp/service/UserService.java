package com.example.todoapp.service;

import com.example.todoapp.dto.UserRequestDto;
import com.example.todoapp.dto.UserResponseDto;
import com.example.todoapp.models.User;

public interface UserService {
    UserResponseDto register(UserRequestDto userRequestDto);
    User getUser(Long userId);
}