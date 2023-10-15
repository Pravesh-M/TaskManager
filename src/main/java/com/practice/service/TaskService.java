package com.practice.service;

import com.practice.exception.TaskNotFoundException;
import com.practice.model.Task;
import com.practice.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo repo;

    public void createTask(Task task) {
        repo.save(task);
    }

    public Task getTask(long taskId) throws TaskNotFoundException {
        return repo.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task with Id : "+taskId+" is not present"));
    }

    public List<Task> getTasks() {
        return repo.findAll();
    }

    public void updateTask(long taskId, boolean isCompleted) throws TaskNotFoundException {
        Task dbTask = getTask(taskId);
        dbTask.setCompleted(isCompleted);
        repo.save(dbTask);

    }

    public void deleteTask(long taskId) {
        repo.deleteById(taskId);
    }
}
