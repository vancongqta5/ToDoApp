package com.example.todoapp.service;

import com.example.todoapp.models.User;
import com.example.todoapp.dto.UserDto;
import com.example.todoapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserDto userDto) throws Exception {
        String username = userDto.getUsername();
        String email = userDto.getEmail();

        // Kiểm tra xem user đã tồn tại chưa
        if (userRepository.existsByUsername(username)) {
            throw new Exception("Tên đăng nhập đã được sử dụng!");
        }
        if (userRepository.existsByEmail(email)) {
            throw new Exception("Email đã được sử dụng!");
        }

        // Mã hóa mật khẩu trước khi lưu vào database
        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());

        // Tạo một đối tượng User mới
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setEncryptedPassword(encryptedPassword);

        // Lưu user vào database và trả về đối tượng User đã lưu
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getEncryptedPassword(), new ArrayList<>());
    }
}
