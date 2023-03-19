package com.example.todoapp.services;

import com.example.todoapp.entity.Role;
import com.example.todoapp.entity.Users;
import com.example.todoapp.repositorys.RoleRepository;
import com.example.todoapp.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceIml implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users saveUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }


    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public void addToUser(String username, String rolename) {
        Users users = userRepository.findByEmail(username);
        Role role = roleRepository.findByName(rolename);
        users.getRoles().add(role);
    }
}
