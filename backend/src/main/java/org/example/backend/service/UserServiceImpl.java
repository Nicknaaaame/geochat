package org.example.backend.service;

import org.example.backend.domain.User;
import org.example.backend.repository.UserRepository;
import org.example.backend.security.userinfo.AbstractOAuth2UserInfo;
import org.example.backend.security.userinfo.ProviderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User saveUserFrom(AbstractOAuth2UserInfo userInfo) {
        return saveUser(new User(null, userInfo.getProviderId(), userInfo.getUsername(), userInfo.getEmail(), userInfo.getPictureUrl()));
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
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
}
