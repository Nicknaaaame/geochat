package org.example.backend.service;

import org.example.backend.model.entity.User;

import java.util.List;

public interface BlackListService {
    void blockUser(Long userId, Long chatId);

    void unblockUser(Long userId, Long chatId);

    Boolean isUserBlocked(Long chatId);

    List<User> getBlackList(Long chatId);
}
