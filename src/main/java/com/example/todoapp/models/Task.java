package com.example.todoapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data // tự động tạo các phương thức getters, setters và toString
@AllArgsConstructor // tự động tạo constructor với toàn bộ các tham số
@NoArgsConstructor // tự động tạo constructor không tham số
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int priority;
    private LocalDateTime createdTime;
    private LocalDateTime completedTime;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TaskCategory category;
}
