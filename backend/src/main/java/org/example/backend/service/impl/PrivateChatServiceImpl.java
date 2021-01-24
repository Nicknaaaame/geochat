package org.example.backend.service.impl;

import org.example.backend.exception.UserNotFoundException;
import org.example.backend.model.entity.Message;
import org.example.backend.model.entity.PrivateChat;
import org.example.backend.model.entity.User;
import org.example.backend.model.entity.enums.MessageType;
import org.example.backend.model.request.SavePrivateChatRequest;
import org.example.backend.repository.PrivateChatRepository;
import org.example.backend.service.MessageService;
import org.example.backend.service.PrivateChatService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class PrivateChatServiceImpl implements PrivateChatService {
    @Autowired
    private PrivateChatRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @Override
    @Transactional
    public PrivateChat savePrivateChat(SavePrivateChatRequest request) {
        User currentUser = userService.getUser();
        PrivateChat privateChat = repository.save(new PrivateChat(null, Set.of(currentUser, userService
                .getUserById(request.getUserId()).orElseThrow(() -> new UserNotFoundException(request.getUserId())))));
        messageService.saveMessage(new Message(
                null,
                request.getMessage(),
                MessageType.TEXT,
                null,
                privateChat,
                currentUser));
        return privateChat;
    }

    @Override
    public List<PrivateChat> getUserPrivateChats() {
        return repository.findByUsersIn(Set.of(userService.getUser()));
    }
}
