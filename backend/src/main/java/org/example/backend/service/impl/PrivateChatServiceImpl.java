package org.example.backend.service.impl;

import org.example.backend.exception.PrivateChatNotFoundException;
import org.example.backend.model.dto.PrivateChatDto;
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
import java.util.stream.Collectors;

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
                .getUserById(request.getUserId()))));
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
    public List<PrivateChatDto> getUserPrivateChats() {
        User currentUser = userService.getUser();
        return repository.findByUsersIn(Set.of(userService.getUser())).stream()
                .map(privateChat -> new PrivateChatDto(
                        privateChat.getId(),
                        privateChat.getUsers().stream().filter(user -> !user.getId().equals(currentUser.getId()))
                                .findFirst().orElseThrow(() -> new RuntimeException("This chat contains the same users"))))
                .collect(Collectors.toList());
    }

    @Override
    public PrivateChat getPrivateChat(Long id) {
        return repository.findById(id).orElseThrow(() -> new PrivateChatNotFoundException(id));
    }
}
