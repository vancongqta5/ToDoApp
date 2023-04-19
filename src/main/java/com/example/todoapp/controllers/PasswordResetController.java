package com.example.todoapp.controllers;

import com.example.todoapp.models.PasswordResetToken;
import com.example.todoapp.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/passwordReset")
@RequiredArgsConstructor
public class PasswordResetController {
    private final PasswordResetService passwordResetService;

    @PostMapping("/token")
    public ResponseEntity<Map<String, Object>> generateToken(@RequestParam("email") String email) {
        PasswordResetToken token = passwordResetService.generateToken(email);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("token", token.getToken());
        responseBody.put("expiryDate", token.getExpiryDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetPassword(@RequestParam("token") String token,
                                              @RequestParam("password") String newPassword) {
        passwordResetService.resetPassword(token, newPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Void> changePassword(@RequestParam("currentPassword") String currentPassword,
                                               @RequestParam("newPassword") String newPassword) {
        passwordResetService.changePassword(currentPassword,newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
