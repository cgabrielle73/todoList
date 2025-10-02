package com.todolist.todolist.services;

import com.todolist.todolist.entities.Tasks;
import com.todolist.todolist.enums.StatusEnum;
import com.todolist.todolist.repository.TasksRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    private TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Transactional
    public Tasks createTasks(Tasks tasks) {
        if(tasks.getTitle().isEmpty() || tasks.getStatus() == null) {
            throw new RuntimeException("Title e Status são obrigatórios");
        }

        Tasks newTask = new Tasks();
        newTask.setTitle(tasks.getTitle());
        newTask.setStatus(StatusEnum.PENDING);
        newTask.setDescription(tasks.getDescription() != null ? tasks.getDescription() : "");
        return tasksRepository.save(newTask);
    }

    @Transactional
    public Tasks updateStatusOfTasks(Tasks tasks) {
        if(tasks.getTitle().isEmpty() || tasks.getStatus() == null) {
            throw new RuntimeException("Title e Status são obrigatórios");
        }

        Optional<Tasks> idTask = tasksRepository.findById(tasks.getId());
        if (idTask.isEmpty()) {
            throw new RuntimeException("Tarefa não encontrada com id: " + tasks.getId());
        }
        if(tasks.isCompleted()) {
            Tasks taskToUpdate = idTask.get();
            taskToUpdate.setStatus(StatusEnum.COMPLETED);
            taskToUpdate.setCompleted(tasks.isCompleted());

            return tasksRepository.save(taskToUpdate);
        } else {
            Tasks taskToUpdate = idTask.get();
            taskToUpdate.setStatus(StatusEnum.PENDING);
            taskToUpdate.setCompleted(tasks.isCompleted());

            return tasksRepository.save(taskToUpdate);
        }
    }

    public List<Tasks> getTasksByIds(Long[] ids) {
        List<Long> idsList = Arrays.asList(ids);
        return tasksRepository.findAllById(idsList);
    }

    public List<Tasks> takeTasks() {
        return tasksRepository.findAll();
    }
}
