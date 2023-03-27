package com.example.todoapp.controllers;

import com.example.todoapp.models.TaskCategory;
import com.example.todoapp.service.TaskCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-categories")
public class TaskCategoryController {

    @Autowired
    private TaskCategoryService taskCategoryService;

    @GetMapping("")
    public List<TaskCategory> getAllTaskCategories() {
        return taskCategoryService.getAllTaskCategories();
    }

    @GetMapping("/{id}")
    public TaskCategory getTaskCategoryById(@PathVariable("id") Long id) {
        return taskCategoryService.getTaskCategoryById(id);
    }

    @PostMapping("")
    public TaskCategory createTaskCategory(@RequestBody TaskCategory taskCategory) {
        return taskCategoryService.saveTaskCategory(taskCategory);
    }

    @PutMapping("/{id}")
    public TaskCategory updateTaskCategory(@PathVariable("id") Long id, @RequestBody TaskCategory taskCategory) {
        TaskCategory existingTaskCategory = taskCategoryService.getTaskCategoryById(id);
        BeanUtils.copyProperties(taskCategory, existingTaskCategory, "id");
        return taskCategoryService.saveTaskCategory(existingTaskCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskCategory(@PathVariable("id") Long id) {
        taskCategoryService.deleteTaskCategoryById(id);
    }
}
