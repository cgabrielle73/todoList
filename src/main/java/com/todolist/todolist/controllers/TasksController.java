package com.todolist.todolist.controllers;

import com.todolist.todolist.entities.Tasks;
import com.todolist.todolist.services.TasksService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TasksController {
    private TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @PostMapping
    public Tasks createNewTask(@RequestBody Tasks tasks) {
        return tasksService.createTasks(tasks);
    }

    @PatchMapping
    public Tasks updateStatus(@RequestBody Tasks tasks) {
        return tasksService.updateStatusOfTasks(tasks);
    }

    @GetMapping("/by-id")
    public List<Tasks> getTasks(@RequestParam("id") Long[] idsTasks) {
        return tasksService.getTasksByIds(idsTasks);
    }

    @GetMapping
    public List<Tasks> getEveryTasks() {
        return tasksService.takeTasks();
    }
}
