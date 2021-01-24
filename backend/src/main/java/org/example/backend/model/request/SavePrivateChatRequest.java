package org.example.backend.model.request;

import lombok.Data;

@Data
public class SavePrivateChatRequest {
    private Long userId;
    private String message;
}
