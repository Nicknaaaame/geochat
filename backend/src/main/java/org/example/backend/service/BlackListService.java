package org.example.backend.service;

import org.example.backend.model.entity.User;

import java.util.List;

public interface BlackListService {
    void blockUser(Long userId);

    void unblockUser(Long userId);

    List<User> getBlackList();

    List<User> getBlackList(User user);

    /**
     * @return list of users who blocked the current user
     * */
    List<User> getBlockedList();

    boolean isUserInBlackList(User user);

    /**
     * @param user another user
     * @return true - if current user is blocked by another user; false - otherwise
     */
    boolean isUserBlocked(User user);
}
