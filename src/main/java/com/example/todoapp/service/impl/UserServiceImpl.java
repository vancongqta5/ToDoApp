package com.example.todoapp.service.impl;

import com.example.todoapp.dto.UserRequestDto;
import com.example.todoapp.dto.UserResponseDto;
import com.example.todoapp.exception.userException.UserNotValidException;
import com.example.todoapp.models.Role;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.RoleRepository;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        //set username, mail, pw
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        // Kiểm tra xem username hoặc email đã tồn tại trong database chưa
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserNotValidException(HttpStatus.CONFLICT.value(), "Username already exists");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserNotValidException(HttpStatus.CONFLICT.value(), "Email already exists");
        }

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));
        user.setRoles(Collections.singletonList(role));

        User savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setUsername(savedUser.getUsername());
        userResponseDto.setEmail(savedUser.getEmail());
//        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setRolenames(role.getName()); // set tên của role cho userResponseDto
        userResponseDto.setLocked(savedUser.getLocked());
        return userResponseDto;
    }
}
