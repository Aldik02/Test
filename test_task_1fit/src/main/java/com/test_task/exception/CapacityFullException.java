package com.test_task.exception;

public class CapacityFullException extends RuntimeException {
    public CapacityFullException(String message) {
        super(message);
    }
}
