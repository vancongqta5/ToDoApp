package com.example.todoapp.controllers;

import com.example.todoapp.dto.ApiResponse;
import com.example.todoapp.dto.TaskRequestDto;
import com.example.todoapp.dto.TaskResponseDto;
import com.example.todoapp.models.Task;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto createTask(@RequestBody TaskRequestDto taskRequestDto) {
        return taskService.createTask(taskRequestDto);
    }
    @GetMapping("/id/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok().body(new ApiResponse(true, "Task with id " + id + " has been deleted."));
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<TaskResponseDto> updateTaskById(@PathVariable Long id, @RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto updatedTask = taskService.updateTaskById(id, taskRequestDto);
        return ResponseEntity.ok(updatedTask);
    }
}

