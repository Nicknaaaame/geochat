package org.example.backend.controller;

import org.example.backend.model.dto.LocationDto;
import org.example.backend.model.request.UpdateProfileRequest;
import org.example.backend.model.response.ProfileResponse;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile() {
        return new ResponseEntity<>(userService.fetchProfile(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProfileResponse> updateProfile(@ModelAttribute UpdateProfileRequest profileRequest) {
        return new ResponseEntity<>(userService.updateProfile(profileRequest), HttpStatus.OK);
    }

    @PutMapping("/location")
    public ResponseEntity<ProfileResponse> updateProfile(@RequestBody LocationDto location) {
        return new ResponseEntity<>(userService.updateProfile(location), HttpStatus.OK);
    }
}
