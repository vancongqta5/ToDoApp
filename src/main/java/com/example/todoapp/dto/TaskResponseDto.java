package com.example.todoapp.dto;

import com.example.todoapp.models.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdTime;
    private LocalDateTime completedTime;
    private boolean completed;

    public TaskResponseDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.createdTime = task.getCreatedTime();
        this.completedTime = task.getCompletedTime();
        this.completed = task.isCompleted();
    }

    public TaskResponseDto(Long id, String name, String description, boolean completed, LocalDateTime createdTime, LocalDateTime completedTime) {
    }
}

