package org.example.backend.exception;

public class LocalChatNotFoundException extends RuntimeException {
    public LocalChatNotFoundException(String message) {
        super(message);
    }

    public LocalChatNotFoundException(Long id) {
        super("Local chat with id: " + id + " not found");
    }
}
