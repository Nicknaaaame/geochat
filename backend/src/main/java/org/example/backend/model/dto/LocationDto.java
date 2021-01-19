package org.example.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDto {
    private Long id;
    private Double longitude;
    private Double latitude;
}
