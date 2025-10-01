package com.todolist.todolist.controllers;

import com.todolist.todolist.entities.Tasks;
import com.todolist.todolist.services.TasksService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
