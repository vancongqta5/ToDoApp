package com.example.todoapp.service;

import com.example.todoapp.models.TaskCategory;

import java.util.List;

public interface TaskCategoryService {
    List<TaskCategory> getAllTaskCategories();
    TaskCategory getTaskCategoryById(Long id);
    TaskCategory saveTaskCategory(TaskCategory taskCategory);
    void deleteTaskCategoryById(Long id);
}
