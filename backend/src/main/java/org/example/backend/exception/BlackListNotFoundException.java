package org.example.backend.exception;

public class BlackListNotFoundException extends RuntimeException {
    public BlackListNotFoundException(String message) {
        super(message);
    }
}
