package com.example.todoapp.service.iService;

import com.example.todoapp.dto.requestDto.TaskRequestDto;
import com.example.todoapp.dto.responseDto.TaskResponseDto;
import com.example.todoapp.models.Task;

import java.util.List;

public interface TaskService {
    Task getTaskById(Long id);
    void deleteTaskById(Long id);
    TaskResponseDto createTask(TaskRequestDto taskRequestDto);
    TaskResponseDto updateTaskById(Long id, TaskRequestDto taskRequestDto);

    List<TaskResponseDto> getAllTasks();

}
