package com.example.todoapp.service.iService;

import com.example.todoapp.models.PasswordResetToken;

public interface PasswordResetService {
    public PasswordResetToken generateToken(String email);

    public void resetPassword(String token, String newPassword);

    public void changePassword(String currentPassword, String newPassword);
}