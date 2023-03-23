package com.example.todoapp.service;

import com.example.todoapp.dto.RegistrationRequest;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User registerUser(RegistrationRequest registrationRequest) throws Exception {
        // kiểm tra xem tên đăng nhập hoặc email đã được sử dụng hay chưa
        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            throw new Exception("Tên đăng nhập đã được sử dụng");
        }
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new Exception("Email đã được sử dụng");
        }

        // tạo đối tượng User mới từ RegistrationRequest
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        // lưu User mới vào cơ sở dữ liệu
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {

    }
}