package com.example.todoapp.dto;

import com.example.todoapp.models.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskResponseDto {
    private long id;
    private String name;
    private String description;
    private LocalDate createdTime;
    private LocalDate completedTime;
    private Boolean isCompleted;

    public TaskResponseDto(Task task) {
    }
//    private Long taskListId;
}

