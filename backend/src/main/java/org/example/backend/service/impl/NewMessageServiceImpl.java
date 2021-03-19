package org.example.backend.service.impl;

import org.example.backend.model.entity.NewMessage;
import org.example.backend.model.request.SaveMessageRequest;
import org.example.backend.repository.NewMessageRepository;
import org.example.backend.service.ChatService;
import org.example.backend.service.NewMessageService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewMessageServiceImpl implements NewMessageService {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @Autowired
    private NewMessageRepository messageRepository;

    @Override
    public NewMessage saveMessage(SaveMessageRequest request) {
        NewMessage message = new NewMessage(
                null,
                request.getText(),
                userService.getUser(),
                chatService.getChat(request.getChatId()),
                LocalDateTime.now()
        );
        return messageRepository.save(message);
    }

    @Override
    public List<NewMessage> getMessages(Long chatId) {
        return messageRepository.findByChatIdEquals(chatId);
    }
}
