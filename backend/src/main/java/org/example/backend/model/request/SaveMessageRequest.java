package org.example.backend.model.request;

import lombok.Data;

@Data
public class SaveMessageRequest {
    private Long chatId;
    private String text;
}
