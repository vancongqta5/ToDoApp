package com.example.todoapp.service.impl;

import com.example.todoapp.models.TaskCategory;
import com.example.todoapp.repository.TaskCategoryRepository;
import com.example.todoapp.service.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @Override
    public List<TaskCategory> getAllTaskCategories() {
        return taskCategoryRepository.findAll();
    }

    @Override
    public TaskCategory getTaskCategoryById(Long id) {
        return taskCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task category not found with id " + id));
    }

    @Override
    public TaskCategory saveTaskCategory(TaskCategory taskCategory) {
        return taskCategoryRepository.save(taskCategory);
    }

    @Override
    public void deleteTaskCategoryById(Long id) {
        taskCategoryRepository.deleteById(id);
    }
}
