package com.example.todoapp.service.iService;

import com.example.todoapp.dto.responseDto.UserResponseDto;

import java.util.List;

public interface AdminService {
    List<UserResponseDto> getAllUser();
    UserResponseDto blockUser(Long userId);
    UserResponseDto getUserById(Long userId);

}
