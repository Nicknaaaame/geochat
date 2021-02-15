package org.example.backend.model.request;

import lombok.Data;
import org.example.backend.validator.BlackListCheck;

@Data
public class SavePrivateMessageRequest {
    @BlackListCheck
    private Long chatId;
    private String text;
}
