package com.example.todoapp.service;

import com.example.todoapp.models.Task;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task saveTask(Task task);
    void deleteTaskById(Long id);
}
