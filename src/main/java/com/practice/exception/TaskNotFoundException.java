package com.practice.exception;

public class TaskNotFoundException extends Exception {
    String message;

    public TaskNotFoundException(String message) {
        super(message);
    }
}
