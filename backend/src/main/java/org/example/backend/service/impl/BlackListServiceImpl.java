package org.example.backend.service.impl;

import org.example.backend.exception.BlackListNotFoundException;
import org.example.backend.model.entity.BlackList;
import org.example.backend.model.entity.Message;
import org.example.backend.model.entity.User;
import org.example.backend.model.entity.enums.MessageType;
import org.example.backend.repository.BlackListRepository;
import org.example.backend.service.BlackListService;
import org.example.backend.service.MessageService;
import org.example.backend.service.PrivateChatService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlackListServiceImpl implements BlackListService {
    @Autowired
    private BlackListRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PrivateChatService privateChatService;

    @Override
    @Transactional
    public void blockUser(Long userId) {
        User currentUser = userService.getUser();
        User userById = userService.getUserById(userId);
        repository.save(new BlackList(null, currentUser, userById));
        privateChatService.getPrivateChat(userById).ifPresent(privateChat ->
                messageService.saveMessage(new Message(
                        null,
                        "Added in black list",
                        MessageType.BLOCK,
                        null,
                        privateChat,
                        currentUser
                )));
    }

    @Override
    public void unblockUser(Long userId) {
        User currentUser = userService.getUser();
        BlackList entry = repository.findByBlockerAndBlocked(currentUser, userService.getUserById(userId))
                .orElseThrow(() -> new BlackListNotFoundException("User is not blocked"));
        repository.delete(entry);
        privateChatService.getPrivateChat(userService.getUserById(userId)).ifPresent(privateChat ->
                messageService.saveMessage(new Message(
                        null,
                        "Removed from black list",
                        MessageType.UNBLOCK,
                        null,
                        privateChat,
                        currentUser
                )));
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
