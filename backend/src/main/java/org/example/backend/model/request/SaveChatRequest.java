package org.example.backend.model.request;

import lombok.Data;

import java.util.Set;

@Data
public class SaveChatRequest {
    private String name;
    private Set<Long> users;
}
