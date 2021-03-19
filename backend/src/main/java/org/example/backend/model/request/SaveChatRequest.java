package org.example.backend.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveChatRequest {
    private String name;
    private String description;
    private String picture;
}
