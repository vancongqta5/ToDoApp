package com.example.todoapp.repository;

import com.example.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId); // Tìm kiếm danh sách Task của User theo userId
    Task save(Task task); // Lưu Task vào cơ sở dữ liệu
    void deleteById(Long id); // Xóa Task theo id
}