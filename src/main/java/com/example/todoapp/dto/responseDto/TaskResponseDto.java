package com.example.todoapp.dto.responseDto;

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

    public TaskResponseDto(Long id, String name, String description, boolean completed, LocalDateTime createdTime, LocalDateTime completedTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.createdTime = createdTime;
        this.completedTime = completedTime;
    }
}

