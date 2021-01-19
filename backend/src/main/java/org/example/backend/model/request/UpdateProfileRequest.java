package org.example.backend.model.request;

import lombok.Data;
import org.example.backend.model.dto.LocationDto;

@Data
public class UpdateProfileRequest {
    private String name;
    private String email;
    private String picture;
    private Boolean showLocation;
    private LocationDto location;
}
