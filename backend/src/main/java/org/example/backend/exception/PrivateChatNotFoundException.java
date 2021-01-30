package org.example.backend.exception;

public class PrivateChatNotFoundException extends RuntimeException {
    public PrivateChatNotFoundException(String message) {
        super(message);
    }

    public PrivateChatNotFoundException(Long id) {
        super("Private chat with id: " + id + " not found");
    }
}
