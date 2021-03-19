package org.example.backend.exception;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(String message) {
        super(message);
    }

    public ChatNotFoundException(Long id) {
        super("Chat with id: " + id + " not found");
    }
}
