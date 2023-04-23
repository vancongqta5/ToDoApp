package com.example.todoapp.service.impl;

import com.example.todoapp.dto.UserResponseDto;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.AdminService;
import com.example.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserService userService;
    private final UserRepository userRepository;
    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userService.getUser(userId);
        return userToUserResponseDto(user);
    }

    @Override
    public UserResponseDto blockUser(Long userId) {
        User user =userService.getUser(userId);
        user.setLocked(true);
        userRepository.save(user);
        return userToUserResponseDto(user);

    }
}
