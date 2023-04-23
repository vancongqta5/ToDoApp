package com.example.todoapp.service.impl;

import com.example.todoapp.dto.UserRequestDto;
import com.example.todoapp.dto.UserResponseDto;
import com.example.todoapp.models.Role;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.RoleRepository;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));
        user.setRoles(Collections.singletonList(role));

        User savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setUsername(savedUser.getUsername());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setRolenames(role.getName()); // set tên của role cho userResponseDto
        return userResponseDto;
    }
}
