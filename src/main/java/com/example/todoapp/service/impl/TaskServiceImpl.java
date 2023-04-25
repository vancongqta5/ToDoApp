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
    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(HttpStatus.NOT_FOUND.value(),"Task not found with id " + id));
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(task.getId());
        taskResponseDto.setName(task.getName());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setCreatedTime(task.getCreatedTime());
        taskResponseDto.setCompletedTime(task.getCompletedTime());
        taskResponseDto.setCompleted(task.isCompleted());
        return taskResponseDto;
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
