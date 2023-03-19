package com.example.todoapp;

import com.example.todoapp.entity.Role;
import com.example.todoapp.entity.Users;
import com.example.todoapp.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class ToDoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoAppApplication.class, args);
    }
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPPER_ADMIN"));

            userService.saveUser(new Users(null, "Cong Nguyen", "nvcong", "vancongqta5@gmail.com", "123456", new HashSet<>()));
            userService.saveUser(new Users(null, "Nguyen", "nvnguyen", "nguyen@gmail.com", "123456", new HashSet<>()));

            userService.addToUser("vancongqta5@gmail.com", "ROLE_USER");
            userService.addToUser("vancongqta5@gmail.com", "ROLE_MANAGER");

            userService.addToUser("nguyen@gmail.com", "ROLE_USER");
            userService.addToUser("nguyen@gmail.com", "ROLE_MANAGER");
        };
    }
}
