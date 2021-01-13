package org.example.backend.service;


import org.example.backend.domain.User;
import org.example.backend.security.userinfo.AbstractOAuth2UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    User saveUserFrom(AbstractOAuth2UserInfo userInfo);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();

    void deleteUserById(Long id);

    Optional<User> getUserByEmail(String email);
}