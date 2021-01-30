package org.example.backend.model.request;

import lombok.Data;

@Data
public class SaveLocalMessageRequest {
    private Long chatId;
    private String text;
}
