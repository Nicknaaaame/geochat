package org.example.backend.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveMessageRequest {
    private Long chatId;
    private String text;
}
