package org.example.backend.service.impl;

import org.example.backend.model.entity.Message;
import org.example.backend.model.entity.enums.MessageType;
import org.example.backend.model.request.SaveMessageRequest;
import org.example.backend.repository.MessageRepository;
import org.example.backend.service.ChatService;
import org.example.backend.service.MessageService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message saveMessage(SaveMessageRequest request) {
        Message message = new Message(
                null,
                request.getText(),
                userService.getUser(),
                chatService.getChat(request.getChatId()),
                MessageType.TEXT,
                LocalDateTime.now()
        );
        return messageRepository.save(message);
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessages(Long chatId) {
        return messageRepository.findByChatIdEquals(chatId);
    }
}
