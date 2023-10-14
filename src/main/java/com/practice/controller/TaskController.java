package com.practice.controller;

import com.practice.model.Task;
import com.practice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        service.createTask(task);
        return new ResponseEntity<>("New Task Created", HttpStatus.CREATED);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable long taskId){
        return new ResponseEntity<>(service.getTask(taskId), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getTasks() {
        return new ResponseEntity<>(service.getTasks(), HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}/{isCompleted}")
    public ResponseEntity<String> updateTask(@PathVariable long taskId, @PathVariable boolean isCompleted) {
        service.updateTask(taskId,isCompleted);
        return new ResponseEntity<>("Task Updated", HttpStatus.OK);

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable long taskId) {
        service.deleteTask(taskId);
        return new ResponseEntity<>("Task Deleted", HttpStatus.OK);
    }
}
