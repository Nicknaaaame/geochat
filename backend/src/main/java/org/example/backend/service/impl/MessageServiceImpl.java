package org.example.backend.service.impl;

import org.example.backend.model.entity.LocalChat;
import org.example.backend.model.entity.Message;
import org.example.backend.model.entity.PrivateChat;
import org.example.backend.model.entity.enums.MessageType;
import org.example.backend.model.request.SaveLocalMessageRequest;
import org.example.backend.model.request.SavePrivateMessageRequest;
import org.example.backend.repository.MessageRepository;
import org.example.backend.service.LocalChatService;
import org.example.backend.service.MessageService;
import org.example.backend.service.PrivateChatService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository repository;
    @Autowired
    private PrivateChatService privateChatService;
    @Autowired
    private LocalChatService localChatService;
    @Autowired
    private UserService userService;

    @Override
    public Message saveMessage(SavePrivateMessageRequest request) {
        PrivateChat privateChat = privateChatService.getPrivateChat(request.getChatId());
        return repository.save(new Message(null, request.getText(), MessageType.TEXT, null, privateChat, userService.getUser()));
    }

    @Override
    public Message saveMessage(SaveLocalMessageRequest request) {
        LocalChat localChat = localChatService.getLocalChat(request.getChatId());
        return repository.save(new Message(null, request.getText(), MessageType.TEXT, localChat, null, userService.getUser()));
    }

    @Override
    public Message saveMessage(Message message) {
        return repository.save(message);
    }
}
