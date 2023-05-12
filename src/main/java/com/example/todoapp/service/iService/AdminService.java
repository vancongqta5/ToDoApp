package com.example.todoapp.service.iService;

import com.example.todoapp.dto.responseDto.UserResponseDto;

import java.util.List;

public interface AdminService {
    List<UserResponseDto> getAllUser();
    public UserResponseDto blockUser(Long userId);
    public UserResponseDto getUserById(Long userId);

}
