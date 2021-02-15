package org.example.backend.service.impl;

import org.example.backend.exception.BlackListNotFoundException;
import org.example.backend.model.entity.BlackList;
import org.example.backend.model.entity.User;
import org.example.backend.repository.BlackListRepository;
import org.example.backend.service.BlackListService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        BlackList entry = repository.findByBlockerAndBlocked(userService.getUser(), userService.getUserById(userId))
                .orElseThrow(() -> new BlackListNotFoundException("User is not blocked"));
        repository.delete(entry);
    }

    @Override
    public List<User> getBlackList() {
        return getBlackList(userService.getUser());
    }

    @Override
    public List<User> getBlackList(User user) {
        return repository.findAllByBlocker(user).stream().map(BlackList::getBlocked).collect(Collectors.toList());
    }

    @Override
    public List<User> getBlockedList() {
        return repository.findAllByBlocked(userService.getUser()).stream().map(BlackList::getBlocker).collect(Collectors.toList());
    }

    @Override
    public boolean isUserInBlackList(User user) {
        return this.getBlackList().stream().anyMatch(entry -> entry.getId().equals(user.getId()));
    }

    @Override
    public boolean isUserBlocked(User user) {
        return this.getBlockedList().stream().anyMatch(entry -> entry.getId().equals(user.getId()));
    }
}
