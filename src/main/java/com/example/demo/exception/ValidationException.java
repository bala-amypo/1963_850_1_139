package com.example.demo.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message); // Message will contain keywords like "exists", "> 0", "0-100"
    }
}