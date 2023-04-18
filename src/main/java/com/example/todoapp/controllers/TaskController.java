package com.example.todoapp.controllers;

import com.example.todoapp.models.Task;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.TaskServiceImpl;
import com.example.todoapp.exception.ResourceNotFoundException;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.beans.PropertyDescriptor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Task>> getTasksByName(@PathVariable String name) {
        List<Task> tasks = taskService.getTaskByName(name);
        if (tasks.isEmpty()) {
            throw new ResourceNotFoundException("Task not found with name " + name);
        }
        return ResponseEntity.ok(tasks);
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        // Set current date and time as created time
        task.setCreatedTime(LocalDateTime.now());
        return taskService.saveTask(task);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Task> updateTaskByPatch(@PathVariable("id") Long id, @RequestBody Task partialUpdate) {
        Task existingTask = taskService.getTaskById(id);
        if (existingTask == null) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        BeanUtils.copyProperties(partialUpdate, existingTask, getNullPropertyNames(partialUpdate));
        Task updatedTask = taskService.saveTask(existingTask);
        return ResponseEntity.ok(updatedTask);
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(PropertyDescriptor::getName)
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }

    @PutMapping("/put/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task existingTask = taskService.getTaskById(id);
        if (existingTask == null) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        BeanUtils.copyProperties(updatedTask, existingTask, "id");
        return taskService.saveTask(existingTask);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTaskById(@PathVariable Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
    }
}
