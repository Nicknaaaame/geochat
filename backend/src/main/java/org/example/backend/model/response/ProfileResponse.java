package org.example.backend.model.response;

import lombok.*;
import org.example.backend.model.entity.Location;

@Getter
@Setter
@Builder
public class ProfileResponse {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private Location location;
}
