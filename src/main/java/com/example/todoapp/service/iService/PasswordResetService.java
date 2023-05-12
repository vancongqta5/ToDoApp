package com.example.todoapp.service.iService;

import com.example.todoapp.models.PasswordResetToken;

public interface PasswordResetService {
    PasswordResetToken generateToken(String email);

    void resetPassword(String token, String newPassword);

    void changePassword(String currentPassword, String newPassword);
}