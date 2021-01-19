package org.example.backend.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User with id: "+id+" not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
