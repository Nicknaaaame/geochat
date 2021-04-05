package org.example.backend.model.response;

import lombok.*;
import org.example.backend.model.entity.Location;
import org.example.backend.model.entity.User;

import java.util.Set;

@Getter
@Setter
@Builder
public class ChatResponse {
    private Long id;
    private String name;
    private String description;
    private String picture;
    private User admin;
    private Set<User> users;
    private Location location;
    private Boolean notification;
}
