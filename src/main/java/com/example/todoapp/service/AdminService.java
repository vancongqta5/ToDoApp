package com.example.todoapp.service;

import com.example.todoapp.dto.UserResponseDto;

public interface AdminService {
    public UserResponseDto blockUser(Long userId);
    public UserResponseDto getUserById(Long userId);

}
