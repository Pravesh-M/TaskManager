package com.practice.service;

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

    public Task getTask(long taskId){
        return repo.findById(taskId).get();
    }

    public List<Task> getTasks() {
        return repo.findAll();
    }

    public void updateTask(long taskId, boolean isCompleted) {
        Task dbTask = repo.findById(taskId).get();
        dbTask.setCompleted(isCompleted);
        repo.save(dbTask);

    }

    public void deleteTask(long taskId) {
        repo.deleteById(taskId);
    }
}
