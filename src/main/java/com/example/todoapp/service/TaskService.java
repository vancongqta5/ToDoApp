package com.example.todoapp.service;

import com.example.todoapp.dto.TaskRequestDto;
import com.example.todoapp.dto.TaskResponseDto;
import com.example.todoapp.models.Task;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);
    TaskResponseDto getTaskById(Long id);
//    List<TaskResponseDto> getAllTasks();
//    List<TaskResponseDto> getTasksByName(String name);
//    TaskResponseDto getTaskById(Long id);
//    TaskResponseDto createTask(TaskRequestDto taskRequestDto);
//    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto);
//    void deleteTaskById(Long id);
//    Task convertToEntity(TaskRequestDto taskRequestDto);
//    TaskResponseDto convertToResponseDto(Task task);

}
