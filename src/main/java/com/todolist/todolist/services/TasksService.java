package com.todolist.todolist.services;

import com.todolist.todolist.entities.Tasks;
import com.todolist.todolist.repository.TasksRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TasksService {
    private TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Transactional
    public Tasks createTasks(Tasks tasks) {
        if(tasks.getTitle().isEmpty() || tasks.getStatus().isEmpty()) {
            throw new RuntimeException("Title e Status são obrigatórios");
        }

        Tasks newTask = new Tasks();
        newTask.setTitle(tasks.getTitle());
        newTask.setStatus(tasks.getStatus());
        newTask.setDescription(tasks.getDescription() != null ? tasks.getDescription() : "");
        return tasksRepository.save(newTask);
    }
}
