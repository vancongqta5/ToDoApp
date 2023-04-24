package com.example.todoapp.dto;

import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
    private LocalDate createdTime;
    private LocalDate completedTime;

    public boolean isCompleted() {
    }
//    private Long taskListId;

}

