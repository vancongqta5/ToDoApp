package com.example.todoapp.controllers;

import com.example.todoapp.dto.TaskRequestDto;
import com.example.todoapp.dto.TaskResponseDto;
import com.example.todoapp.models.Task;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.impl.TaskServiceImpl;
import com.example.todoapp.exception.ResourceNotFoundException;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private TaskRepository taskRepository;
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successfully retrieved list"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                @ApiResponse(code = 404, message = "Task not found with the given ID"),
                @ApiResponse(code = 500, message = "Internal server error")})

        @GetMapping("")
        public List<TaskResponseDto> getAllTasks() {
            List<Task> tasks = taskService.getAllTasks();
            List<TaskResponseDto> responseDtos = tasks.stream().map(TaskResponseDto::new).collect(Collectors.toList());
            return responseDtos;
        }

        @GetMapping("/id/{id}")
        public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
            Task task = taskService.getTaskById(id);
            if (task == null) {
                throw new ResourceNotFoundException("Task not found with id " + id);
            }
            TaskResponseDto responseDto = new TaskResponseDto(task);
            return ResponseEntity.ok(responseDto);
        }

        @GetMapping("/name/{name}")
        public ResponseEntity<List<TaskResponseDto>> getTasksByName(@PathVariable String name) {
            List<Task> tasks = taskService.getTaskByName(name);
            if (tasks.isEmpty()) {
                throw new ResourceNotFoundException("Task not found with name " + name);
            }
            List<TaskResponseDto> responseDtos = tasks.stream().map(TaskResponseDto::new).collect(Collectors.toList());
            return ResponseEntity.ok(responseDtos);
        }


        @PostMapping("")
        @ResponseStatus(HttpStatus.CREATED)
        public TaskResponseDto createTask(@RequestBody TaskRequestDto taskRequestDto) {
            // Convert TaskRequestDto to Task entity
            Task task = new Task();
            task.setName(taskRequestDto.getName());
            task.setDescription(taskRequestDto.getDescription());
            task.setCompleted(taskRequestDto.isCompleted());

            // Set current date and time as created time
            task.setCreatedTime(LocalDateTime.now());

            // Save Task entity to database
            task = taskService.saveTask(task);

            // Convert Task entity to TaskResponseDto
            TaskResponseDto responseDto = new TaskResponseDto(task);
            return responseDto;
        }

        @PatchMapping("/patch/{id}")
        public ResponseEntity<TaskResponseDto> updateTaskByPatch(@PathVariable("id") Long id, @RequestBody TaskRequestDto taskRequestDto) {
            Task existingTask = taskService.getTaskById(id);
            if (existingTask == null) {
                throw new ResourceNotFoundException("Task not found with id " + id);
            }

            // Update Task entity with fields from TaskRequestDto
            if (taskRequestDto.getName() != null) {
                existingTask.setName(taskRequestDto.getName());
            }
            if (taskRequestDto.getDescription() != null) {
                existingTask.setDescription(taskRequestDto.getDescription());
            }
            if (taskRequestDto.isCompleted() != null) {
                existingTask.setCompleted(taskRequestDto.isCompleted());
            }

            // Save updated Task entity to database
            Task updatedTask = taskService.saveTask(existingTask);

            // Convert Task entity to TaskResponseDto
            TaskResponseDto responseDto = new TaskResponseDto(updatedTask);
            return ResponseEntity.ok(responseDto);
        }
