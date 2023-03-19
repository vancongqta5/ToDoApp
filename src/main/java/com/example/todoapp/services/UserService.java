package com.example.todoapp.services;

import com.example.todoapp.entity.Role;
import com.example.todoapp.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Users saveUser(Users users);
    Role saveRole(Role role);
    void addToUser(String username, String rolename);

}
