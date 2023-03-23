package com.example.todoapp.service;

import com.example.todoapp.dto.RegistrationRequest;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(RegistrationRequest registrationRequest) {
        // validate registration request here using Hibernate Validator

        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());

        // perform any additional business logic, such as hashing password or sending confirmation email

        return userRepository.save(user);
    }
}
