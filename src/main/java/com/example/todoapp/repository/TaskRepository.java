package com.example.todoapp.repository;

import com.example.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
//    List<Task> findByName(String name);
    List<Task> findAll();
}
