package org.example.backend.service.impl;


import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.BlackList;
import org.example.backend.model.entity.User;
import org.example.backend.repository.BlackListRepository;
import org.example.backend.service.ChatService;
import org.example.backend.service.BlackListService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlackListServiceImpl implements BlackListService {
    @Autowired
    private BlackListRepository blackListRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;

    @Override
    @Transactional
    public void blockUser(Long userId, Long chatId) {
        Chat chat = chatService.getChat(chatId);
        User user = userService.getUserById(userId);
        chatService.removeUser(chat, user);
        chat = chatService.saveChat(chat);
        BlackList blackList = new BlackList(
                null,
                user,
                chat
        );
        blackListRepository.save(blackList);
    }

    @Override
    @Transactional
    public void unblockUser(Long userId, Long chatId) {
        blackListRepository.deleteByUser_IdAndChat_Id(userId, chatId);
        Chat chat = chatService.getChat(chatId);
        User user = userService.getUserById(userId);
        chatService.addUser(chat, user);
        chatService.saveChat(chat);
    }

    @Override
    public Boolean isUserBlocked(Long chatId) {
        return blackListRepository.existsByUser_IdAndChat_Id(userService.getUser().getId(), chatId);
    }

    @Override
    public List<User> getBlackList(Long chatId) {
        return blackListRepository.findAllByChat_Id(chatId).stream()
                .map(BlackList::getUser)
                .collect(Collectors.toList());
    }
}
