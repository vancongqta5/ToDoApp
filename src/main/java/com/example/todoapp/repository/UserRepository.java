package com.example.todoapp.repository;

import com.example.todoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Tìm kiếm User theo username
    User findByEmail(String email); // Tìm kiếm User theo email

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
//    User save(User user); // Lưu User vào cơ sở dữ liệu
//    void deleteById(Long id); // Xóa User theo id
}