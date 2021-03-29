package org.example.backend.service;


import org.example.backend.model.dto.LocationDto;
import org.example.backend.model.entity.User;
import org.example.backend.model.request.UpdateProfileRequest;
import org.example.backend.model.response.ProfileResponse;
import org.example.backend.security.userinfo.AbstractOAuth2UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    User saveUserFrom(AbstractOAuth2UserInfo userInfo);

    User getUser();

    User getUserById(Long id);

    List<User> getUsersAround();

    List<User> getAllUsers();

    void deleteUserById(Long id);

    Optional<User> getUserByEmail(String email);

    ProfileResponse fetchProfile();

    ProfileResponse updateProfile(UpdateProfileRequest request);

    ProfileResponse updateProfile(LocationDto location);
}
