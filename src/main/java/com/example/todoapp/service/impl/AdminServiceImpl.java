package com.example.todoapp.service.impl;

import com.example.todoapp.dto.responseDto.UserResponseDto;
import com.example.todoapp.exception.userException.UserNotFoundException;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.iService.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserResponseDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), String.join(",", user.getRoleNames()), user.getLocked()))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto blockUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND.value(),"Can not find user with id "+ userId));
        user.setLocked(true);
        userRepository.save(user);
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), String.join(",", user.getRoleNames()), user.getLocked());
    }
}
