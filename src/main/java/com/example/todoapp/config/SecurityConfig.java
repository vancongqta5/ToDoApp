package com.example.todoapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/user/register").permitAll() // Cho phép truy cập đến /api/user/register mà không yêu cầu đăng nhập
                .anyRequest().authenticated() // Các endpoint còn lại yêu cầu đăng nhập
                .and()
                .httpBasic();
    }

}
