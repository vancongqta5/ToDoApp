package com.example.todoapp.service.impl;

import com.example.todoapp.dto.UserResponseDto;
import com.example.todoapp.exception.userException.UserNotFoundException;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND.value(),"Can not find user with id "+ userId));
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), String.join(",", user.getRoleNames()), user.getLocked());
    }

    @Override
    public UserResponseDto blockUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND.value(),"Can not find user with id "+ userId));
        user.setLocked(true);
        userRepository.save(user);
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), String.join(",", user.getRoleNames()), user.getLocked());
    }
}
