package org.example.backend.service;

import org.example.backend.model.entity.User;

import java.util.List;

public interface BlackListService {
    void blockUser(Long userId);

    void unblockUser(Long userId);

    List<User> getBlackList();

    /**
     * @return list of users who blocked the current user
     * */
    List<User> getBlockedList();
}
