package org.example.backend.service.impl;

import org.example.backend.model.entity.BlackList;
import org.example.backend.model.entity.User;
import org.example.backend.repository.BlackListRepository;
import org.example.backend.service.BlackListService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackListServiceImpl implements BlackListService {
    @Autowired
    private BlackListRepository repository;
    @Autowired
    private UserService userService;

    @Override
    public void blockUser(Long userId) {
        repository.save(new BlackList(null, userService.getUser(), userService.getUserById(userId)));
    }

    @Override
    public void unblockUser(Long userId) {

    }

    @Override
    public List<User> getBlackList() {
        return null;
    }

    @Override
    public List<User> getBlockedList() {
        return null;
    }
}
