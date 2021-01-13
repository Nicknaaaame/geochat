package org.example.backend.exception;

import org.springframework.security.core.AuthenticationException;

public class OAuthProcessingException extends AuthenticationException {
    public OAuthProcessingException(String message) {
        super(message);
    }
}
