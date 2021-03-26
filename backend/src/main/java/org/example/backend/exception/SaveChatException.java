package org.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SaveChatException extends ResponseStatusException {
    public SaveChatException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
