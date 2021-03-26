package org.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserIsBlockedException extends ResponseStatusException {
    public UserIsBlockedException() {
        super(HttpStatus.FORBIDDEN, "User is in black list");
    }
}
