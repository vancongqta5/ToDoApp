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

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
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
    //    @Override
//    public List<TaskResponseDto> getAllTasks() {
//        List<Task> tasks = taskRepository.findAll();
//        return tasks.stream()
//                .map(task -> {
//                    TaskResponseDto taskResponseDto = new TaskResponseDto();
//                    taskResponseDto.setId(task.getId());
//                    taskResponseDto.setName(task.getName());
//                    taskResponseDto.setDescription(task.getDescription());
//                    taskResponseDto.setCreatedTime(task.getCreatedTime());
//                    return taskResponseDto;
//                })
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<TaskResponseDto> getTaskByName(String name) {
//        List<Task> tasks = taskRepository.findByName(name);
//        return tasks.stream()
//                .map(task -> {
//                    TaskResponseDto taskResponseDto = new TaskResponseDto();
//                    taskResponseDto.setId(task.getId());
//                    taskResponseDto.setName(task.getName());
//                    taskResponseDto.setDescription(task.getDescription());
//                    taskResponseDto.setCreatedTime(task.getCreatedTime());
//                    return taskResponseDto;
//                })
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public TaskResponseDto getTaskById(Long id) {
//        return null;
//    }
//
//    @Override
//    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
//        return null;
//    }
//
//    @Override
//    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) {
//        return null;
//    }
//
//    @Override
//    public void deleteTaskById(Long id) {
//
//    }
//
//    @Override
//    public Task convertToEntity(TaskRequestDto taskRequestDto) {
//        return null;
//    }
//
//    @Override
//    public TaskResponseDto convertToResponseDto(Task task) {
//        return null;
//    }
}
