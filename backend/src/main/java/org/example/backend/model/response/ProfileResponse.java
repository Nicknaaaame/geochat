package org.example.backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.backend.model.entity.Location;

@Data
@AllArgsConstructor
@Builder
public class ProfileResponse {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private Boolean showLocation;
    private Location location;
}
