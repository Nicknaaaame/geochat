package org.example.backend.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlackListRequest {
    private Long userId;
    private Long chatId;
}
