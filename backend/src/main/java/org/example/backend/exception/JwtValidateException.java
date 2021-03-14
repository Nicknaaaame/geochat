package org.example.backend.exception;

public class JwtValidateException extends RuntimeException {
    public JwtValidateException(String message) {
        super(message);
    }
}
