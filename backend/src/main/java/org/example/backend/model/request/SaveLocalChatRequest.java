package org.example.backend.model.request;

import lombok.Data;

import java.util.Set;

@Data
public class SaveLocalChatRequest {
    private String name;
    private Set<Long> users;
}
