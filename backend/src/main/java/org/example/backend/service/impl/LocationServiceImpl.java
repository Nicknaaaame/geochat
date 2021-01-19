package org.example.backend.service.impl;

import org.example.backend.model.entity.Location;
import org.example.backend.repository.LocationRepository;
import org.example.backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }
}
