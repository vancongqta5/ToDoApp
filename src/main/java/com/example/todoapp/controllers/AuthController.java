package com.example.todoapp.controllers;

import com.example.todoapp.dto.ApiResponse;
import com.example.todoapp.dto.AuthResponseDTO;
import com.example.todoapp.dto.LoginDto;
import com.example.todoapp.models.Role;
import com.example.todoapp.models.UserEntity;
import com.example.todoapp.repository.RoleRepository;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.security.JwtTokenProvider;
import com.example.todoapp.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtGenerator;

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

@PostMapping("register")
public ResponseEntity<ApiResponse> register(@RequestBody @Valid RegisterDto registerDto, BindingResult result, UriComponentsBuilder uriBuilder) {
    if (result.hasErrors()) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.add(error.getField() + " " + error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(new ApiResponse(false, errors.toString()));
    }

    String username = registerDto.getUsername();
    if (userRepository.existsByUsername(username)) {
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Username '" + username + "' is already taken"));
    }

    String email = registerDto.getEmail();
    if (userRepository.existsByEmail(email)) {
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Email '" + email + "' is already registered"));
    }

    UserEntity user = new UserEntity();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    Role role = roleRepository.findByName("USER")
            .orElseThrow(() -> new RuntimeException("USER role not found"));
    user.setRoles(Collections.singletonList(role));

    UserEntity savedUser = userRepository.save(user);

    URI location = uriBuilder.path("/users/{id}").buildAndExpand(savedUser.getId()).toUri();
    return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}