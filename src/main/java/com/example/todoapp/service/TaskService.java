//package com.example.todoapp.service;
//
//import com.example.todoapp.models.Task;
//import com.example.todoapp.repository.TaskRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class TaskService {
//
//    @Autowired
//    private TaskRepository taskRepository;
//
//    public List<Task> getAllTasks() {
//        return taskRepository.findAll();
//    }
//
//    public Task getTaskById(Long id) {
//        Optional<Task> optionalTask = taskRepository.findById(id);
//        return optionalTask.orElse(null);
//    }
//
//    //Thêm task mới
//    public Task addTask(Task task) {
//        return taskRepository.save(task);
//    }
//
//    //Cập nhật task
//    public Task updateTask(Task task) {
//        return taskRepository.save(task);
//    }
//
//    //Xóa task
//    public void deleteTask(Long id) {
//        taskRepository.deleteById(id);
//    }
//}
