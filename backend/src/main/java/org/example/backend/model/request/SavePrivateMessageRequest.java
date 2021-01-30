package org.example.backend.model.request;

import lombok.Data;

@Data
public class SavePrivateMessageRequest {
    private Long chatId;
    private String text;
}
