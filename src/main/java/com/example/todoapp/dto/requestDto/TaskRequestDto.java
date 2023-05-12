package com.example.todoapp.dto.requestDto;

import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TaskRequestDto {
    @NotEmpty
    @Size(min = 1, max = 20)
    private String name;
    @Size(min = 0, max = 500)
    private String description;
    private LocalDateTime createdTime;
    private LocalDateTime completedTime;
//    private boolean isCompleted;
}

