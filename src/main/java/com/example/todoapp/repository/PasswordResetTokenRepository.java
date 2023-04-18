package com.example.todoapp.repository;


import com.example.todoapp.models.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {
    PasswordResetToken findByToken(String token);
    void deleteByExpiryDateLessThan(LocalDateTime now);
}

