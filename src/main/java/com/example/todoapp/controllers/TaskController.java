package com.example.todoapp.controllers;

import com.example.todoapp.dto.TaskRequestDto;
import com.example.todoapp.dto.TaskResponseDto;
import com.example.todoapp.models.Task;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.impl.TaskServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto createTask(@RequestBody TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setName(taskRequestDto.getName());
        task.setDescription(taskRequestDto.getDescription());
        task.setCreatedTime(LocalDateTime.now());
        task.setCompletedTime(taskRequestDto.getCompletedTime());
        task.setCompleted(false);

        Task savedTask = taskService.saveTask(task);

        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(savedTask.getId());
        taskResponseDto.setName(savedTask.getName());
        taskResponseDto.setDescription(savedTask.getDescription());
        taskResponseDto.setCreatedTime(savedTask.getCreatedTime());
        taskResponseDto.setCompletedTime(savedTask.getCompletedTime());
        taskResponseDto.setCompleted(savedTask.isCompleted());

        return taskResponseDto;
    }
    @GetMapping("/id/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
    }
//        return taskService.getTaskById(id);

//    @GetMapping("/getById")
//    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
//        Task task = taskService.getTaskById(id);
//        if (task == null) {
//            throw new ResourceNotFoundException("Task not found with id " + id);
//        }
//        TaskResponseDto taskResponseDto = new TaskResponseDto(task);
//        return ResponseEntity.ok(taskResponseDto);
//    }
//    @GetMapping("/getByName")
//    public ResponseEntity<List<Task>> getTasksByKeyword(@PathVariable String keyword) {
//        List<Task> allTasks = taskService.getAllTasks();
//        List<Task> filteredTasks = allTasks.stream()
//                .filter(task -> task.getName().contains(keyword))
//                .collect(Collectors.toList());
//        if (filteredTasks.isEmpty()) {
//            throw new ResourceNotFoundException("No tasks found with keyword " + keyword);
//        }
//        return ResponseEntity.ok(filteredTasks);
//    }
//
//    @PatchMapping("patch/{id}")
//    public ResponseEntity<TaskResponseDto> updateTaskByPatch(@PathVariable("id") Long id, @RequestBody TaskRequestDto taskRequestDto) {
//        Task task = taskService.getTaskById(id);
//        if (task == null) {
//            throw new ResourceNotFoundException("Task not found with id " + id);
//        }
//
//        BeanUtils.copyProperties(taskRequestDto, task, getNullPropertyNames(taskRequestDto));
//
//        task = taskService.saveTask(task);
//
//        TaskResponseDto taskResponseDto = new TaskResponseDto(task.getId(), task.getName(), task.getDescription(), task.getCreatedTime(), task.getCompletedTime(), task.isCompleted());
//
//        return ResponseEntity.ok(taskResponseDto);
//    }
//    private static String[] getNullPropertyNames(Object source) {
//        final BeanWrapper src = new BeanWrapperImpl(source);
//        return Stream.of(src.getPropertyDescriptors())
//                .map(PropertyDescriptor::getName)
//                .filter(name -> src.getPropertyValue(name) == null)
//                .toArray(String[]::new);
//    }
//    @PutMapping("/put/{id}")
//    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto taskRequestDto) {
//        Task existingTask = taskService.getTaskById(id);
//        if (existingTask == null) {
//            throw new ResourceNotFoundException("Task not found with id " + id);
//        }
//        // Copy properties from taskRequestDto to existingTask, except for id and createdTime
//        BeanUtils.copyProperties(taskRequestDto, existingTask, "id", "createdTime");
//
//        // Save the updated task
//        Task updatedTask = taskService.saveTask(existingTask);
//
//        // Convert the updated task to TaskResponseDto
//        TaskResponseDto taskResponseDto = new TaskResponseDto();
//        BeanUtils.copyProperties(updatedTask, taskResponseDto);
//
//        return ResponseEntity.ok(taskResponseDto);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteTaskById(@PathVariable Long id) {
//        Optional<Task> optionalTask = taskRepository.findById(id);
//        if (optionalTask.isPresent()) {
//            taskRepository.deleteById(id);
//            return ResponseEntity.ok().build();
//        } else {
//            throw new ResourceNotFoundException("Task not found with id " + id);
//        }
//    }

