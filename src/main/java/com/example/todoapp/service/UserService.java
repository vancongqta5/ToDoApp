package com.example.todoapp.service;

import com.example.todoapp.dto.RegistrationRequest;
import com.example.todoapp.models.User;

public interface UserService {

    User registerUser(RegistrationRequest registrationRequest) throws Exception;

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void save(User user);
}