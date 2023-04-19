package com.example.todoapp.repository;


import com.example.todoapp.models.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {
    PasswordResetToken findByToken(String token);
}

