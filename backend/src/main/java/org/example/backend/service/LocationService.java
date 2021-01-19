package org.example.backend.service;

import org.example.backend.model.entity.Location;

import java.util.Optional;

public interface LocationService {
    Optional<Location> getLocationById(Long id);
    Location saveLocation(Location location);
}
