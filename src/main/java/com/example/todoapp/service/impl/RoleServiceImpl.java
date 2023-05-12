package com.example.todoapp.service.impl;


import com.example.todoapp.models.Role;
import com.example.todoapp.repository.RoleRepository;
import com.example.todoapp.service.iService.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}