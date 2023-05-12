package com.example.todoapp.service.impl;

import com.example.todoapp.exception.passwordException.InvalidPasswordException;
import com.example.todoapp.exception.passwordException.CurrentPasswordNotMatch;
import com.example.todoapp.exception.passwordException.ResetPasswordTokenNotValidException;
import com.example.todoapp.exception.userException.UserNotFoundException;
import com.example.todoapp.models.PasswordResetToken;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.PasswordResetTokenRepository;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.iService.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private void validatePassword(String newPassword) {
        if (newPassword == null || newPassword.isEmpty() || newPassword.contains(" ")) {
            throw new InvalidPasswordException(HttpStatus.BAD_REQUEST.value(), "Password cannot be empty or contain spaces");
        }

        if (newPassword.length() < 3 || newPassword.length() > 20) {
            throw new InvalidPasswordException(HttpStatus.BAD_REQUEST.value(), "Password must be between 3 and 20 characters");
        }
    }
    @Override
    public PasswordResetToken generateToken(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND.value(),"User not found with email: " + email);
        }
        PasswordResetToken token = new PasswordResetToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        return tokenRepository.save(token);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null) {
            throw new ResetPasswordTokenNotValidException(HttpStatus.BAD_REQUEST.value(),"Invalid token: " + token);
        }

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new ResetPasswordTokenNotValidException(HttpStatus.BAD_REQUEST.value(),"Token has expired");
        }
        // Validate the new password
        validatePassword(newPassword);
//        if (newPassword == null || newPassword.isEmpty() || newPassword.contains(" ")) {
//            throw new InvalidPasswordException(HttpStatus.BAD_REQUEST.value(), "Password cannot be empty or contain spaces");
//        }
//
//        if (newPassword.length() < 3 || newPassword.length() > 20) {
//            throw new InvalidPasswordException(HttpStatus.BAD_REQUEST.value(), "Password must be between 3 and 20 characters");
//        }

        User user = resetToken.getUser();
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        userRepository.save(user);

        tokenRepository.delete(resetToken);
    }

    @Override
    public void changePassword(String currentPassword, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Create an authentication object with the user's credentials
        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(username, currentPassword);

        // Authenticate the user's credentials
        try {
            authenticationManager.authenticate(userAuthentication);
        } catch (AuthenticationException e) {
            throw new CurrentPasswordNotMatch(HttpStatus.BAD_REQUEST.value(),"current pasword is not match.");
        }

        // Validate the new password
        validatePassword(newPassword);

//        if (newPassword == null || newPassword.isEmpty() || newPassword.contains(" ")) {
//            throw new InvalidPasswordException(HttpStatus.BAD_REQUEST.value(), "Password cannot be empty or contain spaces");
//        }
//
//        if (newPassword.length() < 3 || newPassword.length() > 20) {
//            throw new InvalidPasswordException(HttpStatus.BAD_REQUEST.value(), "Password must be between 3 and 20 characters");
//        }
        // Change the user's password
        Optional<User> user = userRepository.findByUsername(username);
        user.get().setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user.get());
    }
}
