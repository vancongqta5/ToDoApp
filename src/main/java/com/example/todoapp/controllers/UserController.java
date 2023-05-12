package com.example.todoapp.controllers;

import com.example.todoapp.dto.requestDto.LoginRequest;
import com.example.todoapp.dto.requestDto.UserRequestDto;
import com.example.todoapp.dto.responseDto.ApiResponse;
import com.example.todoapp.dto.responseDto.LoginResponse;
import com.example.todoapp.dto.responseDto.UserResponseDto;
import com.example.todoapp.exception.userException.UserNotValidException;
import com.example.todoapp.models.Role;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.RoleRepository;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.security.JwtTokenProvider;
import com.example.todoapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        if(user.isPresent() && user.get().getLocked()){
            throw new UserNotValidException(HttpStatus.LOCKED.value(),"Your account has been locked by an administrator.");
        }

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "UserName or Password is not valid"));
        }
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về token và thông tin người dùng cho client
        String token = jwtTokenProvider.generateToken(authentication);

        // Lấy thông tin người dùng và tạo đối tượng UserResponseDto
        User currentUser = userRepository.findByUsername(loginRequest.getUsername()).get();
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(currentUser.getId());
        userResponseDto.setUsername(currentUser.getUsername());
        userResponseDto.setEmail(currentUser.getEmail());
        userResponseDto.setPassword(currentUser.getPassword());
        userResponseDto.setLocked(currentUser.getLocked());
        //để chuyển đổi List thành string
        List<Role> roles = currentUser.getRoles();
        String roleNames = roles.stream()
                .map(Role::getName)
                .collect(Collectors.joining(", "));
        userResponseDto.setRolenames(roleNames);

        return ResponseEntity.ok(new LoginResponse(userResponseDto, token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto userRequestDto, BindingResult bindingResult, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            bindingResult.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage())
            );

            String errorMsg = "";

            for (String key : errors.keySet()) {
                errorMsg += "Error by: " + key + ", reason: " + errors.get(key);
            }
            throw new UserNotValidException(HttpStatus.BAD_REQUEST.value(), errorMsg);

        }

        UserResponseDto userResponseDto = userService.register(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);

    }
}
