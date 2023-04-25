package com.example.todoapp.service.impl;

import com.example.todoapp.dto.TaskRequestDto;
import com.example.todoapp.dto.TaskResponseDto;
import com.example.todoapp.exception.ResourceNotFoundException;
import com.example.todoapp.exception.TaskNotFoundException;
import com.example.todoapp.models.Task;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            throw new TaskNotFoundException(HttpStatus.NOT_FOUND.value(),"Task not found with id " + id);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
//        Task Task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(HttpStatus.NOT_FOUND.value(),"Task not found with id " + id));
        if (taskOptional.isPresent()) {
            taskRepository.deleteById(id);
        } else {
            throw new TaskNotFoundException(HttpStatus.NOT_FOUND.value(),"Task not found with id " + id);
        }
    }
    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setName(taskRequestDto.getName());
        task.setDescription(taskRequestDto.getDescription());
        task.setCreatedTime(LocalDateTime.now());
        task.setCompletedTime(taskRequestDto.getCompletedTime());
        task.setCompleted(false);

        Task savedTask = taskRepository.save(task);

        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(savedTask.getId());
        taskResponseDto.setName(savedTask.getName());
        taskResponseDto.setDescription(savedTask.getDescription());
        taskResponseDto.setCreatedTime(savedTask.getCreatedTime());
        taskResponseDto.setCompletedTime(savedTask.getCompletedTime());
        taskResponseDto.setCompleted(savedTask.isCompleted());

        return taskResponseDto;
    }
    @Override
    public TaskResponseDto updateTaskById(Long id, TaskRequestDto taskRequestDto) {
        // Check if task with given id exists
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        // Update task fields with request data
        existingTask.setName(taskRequestDto.getName());
        existingTask.setDescription(taskRequestDto.getDescription());

        // Save and return updated task
        Task updatedTask = taskRepository.save(existingTask);
        return new TaskResponseDto(updatedTask.getId(), updatedTask.getName(), updatedTask.getDescription(), updatedTask.isCompleted(), updatedTask.getCreatedTime(), updatedTask.getCompletedTime());
    }

}
