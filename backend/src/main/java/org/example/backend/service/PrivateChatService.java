package org.example.backend.service;

import org.example.backend.model.dto.PrivateChatDto;
import org.example.backend.model.entity.PrivateChat;
import org.example.backend.model.entity.User;
import org.example.backend.model.request.SavePrivateChatRequest;

import java.util.List;
import java.util.Optional;

public interface PrivateChatService {
    PrivateChat savePrivateChat(SavePrivateChatRequest request);

    List<PrivateChatDto> getUserPrivateChats();

    PrivateChat getPrivateChat(Long id);

    User getOtherUser(PrivateChat currentUser);

    /**
     * @return private chat with other user
     * */
    Optional<PrivateChat> getPrivateChat(User user);
}
