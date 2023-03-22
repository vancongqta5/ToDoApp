//package com.example.todoapp.service;
//import com.example.todoapp.models.LoginRequest;
//import com.example.todoapp.models.RegisterRequest;
//import com.example.todoapp.models.Users;
//import com.example.todoapp.repository.UserRepository;
//import com.example.todoapp.security.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//    private final AuthenticationManager authenticationManager;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtils jwtUtils;
//
//    @Autowired
//    public AuthService(AuthenticationManager authenticationManager,
//                       UserRepository userRepository,
//                       PasswordEncoder passwordEncoder,
//                       JwtUtils jwtUtils) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtUtils = jwtUtils;
//    }
//
//    public String login(LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtUtils.generateJwtToken(authentication);
//        return token;
//    }
//
//    public void register(RegisterRequest registerRequest) {
//        Users user = new Users();
//        user.setUsername(registerRequest.getUsername());
//        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
//        user.setFullName(registerRequest.getFullName());
//        userRepository.save(user);
//    }
//}