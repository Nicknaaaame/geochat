package org.example.backend.service.impl;

import org.example.backend.converter.ProfileResponseConverter;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.model.dto.LocationDto;
import org.example.backend.model.entity.Location;
import org.example.backend.model.entity.User;
import org.example.backend.model.request.UpdateProfileRequest;
import org.example.backend.model.response.ProfileResponse;
import org.example.backend.repository.UserRepository;
import org.example.backend.security.UserPrincipal;
import org.example.backend.security.userinfo.AbstractOAuth2UserInfo;
import org.example.backend.service.LocationService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final double SEARCH_RADIUS = 10d;
    private static final double EARTH_RADIUS = 6317d;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileResponseConverter profileResponseConverter;
    @Autowired
    private LocationService locationService;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User saveUserFrom(AbstractOAuth2UserInfo userInfo) {
        return saveUser(new User(null, userInfo.getProvidedUserId(), userInfo.getUsername(), userInfo.getEmail(), userInfo.getPictureUrl()));
    }

    @Override
    public User getUser() {
        Long id = fetchUserPrincipal().getId();
        if (id == null)
            throw new AccessDeniedException("Invalid access");
        return getUserById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getUsersAround() {
        Location location = getUser().getLocation();
        double delta = SEARCH_RADIUS / EARTH_RADIUS;
        double lon1 = location.getLongitude() - delta, lon2 = location.getLongitude() + delta;
        double lat1 = location.getLatitude() - delta, lat2 = location.getLatitude() + delta;
        return userRepository.findByLocation_LatitudeBetweenAndLocation_LongitudeBetween(lat1, lat2, lon1, lon2);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public ProfileResponse fetchProfile() {
        return profileResponseConverter.apply(getUser());
    }

    @Override
    public ProfileResponse updateProfile(UpdateProfileRequest request) {
        User user = getUser();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPicture(request.getPicture());
        user.setShowLocation(request.getShowLocation());
        LocationDto location = request.getLocation();
        user.setLocation(locationService.saveLocation(
                new Location(location.getId(), location.getLatitude(), location.getLongitude())));
        return profileResponseConverter.apply(saveUser(user));
    }

    private UserPrincipal fetchUserPrincipal() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
