package com.example.todoapp.repositorys;

import com.example.todoapp.entity.Role;
import com.example.todoapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);

}
