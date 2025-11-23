package com.todo.todoapp.service;

import com.todo.todoapp.model.Task;
import com.todo.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks(String filter) {
        if ("completed".equals(filter)) {
            return taskRepository.findByCompleted(true);
        } else if ("incomplete".equals(filter)) {
            return taskRepository.findByCompleted(false);
        } else {
            return taskRepository.findAll(); // Default to "all"
        }
    }

    public long getRemainingCount() {
        return taskRepository.countByCompleted(false); // Counts incomplete tasks
    }

    public long getTotalCount() {
        return taskRepository.count();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);

        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid task id"));

        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
