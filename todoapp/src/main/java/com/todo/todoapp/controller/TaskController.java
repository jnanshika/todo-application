package com.todo.todoapp.controller;

import com.todo.todoapp.model.Task;
import com.todo.todoapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {

    //dependency injection
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/") // Maps to localhost:8080/
    public String getTasks(@RequestParam(name = "filter", defaultValue = "all") String filter, Model model) {

        // 1. Get the list of tasks based on the filter (All, Completed, or Incomplete)
        List<Task> tasks = taskService.getTasks(filter);

        // 2. Get the counts for the Motivational Banner
        long remainingCount = taskService.getRemainingCount();
        long totalCount = taskService.getTotalCount();

        // 3. Send all this data to Thymeleaf (tasks.html)
        model.addAttribute("tasks", tasks);
        model.addAttribute("filter", filter);           // To highlight the active Tab
        model.addAttribute("remainingCount", remainingCount); // For "X tasks to go"
        model.addAttribute("totalCount", totalCount);     // For "Done for the day"

        return "tasks";
    }

    @PostMapping
    public String createTask(@RequestParam String title){
        taskService.createTask(title);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id){
        taskService.toggleTask(id);
        return "redirect:/";
    }
}
