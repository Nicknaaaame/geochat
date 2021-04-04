package org.example.backend.converter;

import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.UserChats;
import org.example.backend.model.response.ChatResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ChatResponseConverter implements Function<Chat, ChatResponse> {
    @Override
    public ChatResponse apply(Chat chat) {
        return ChatResponse.builder()
                .admin(chat.getAdmin())
                .description(chat.getDescription())
                .id(chat.getId())
                .location(chat.getLocation())
                .name(chat.getName())
                .picture(chat.getPicture())
                .users(chat.getUsers().stream().map(UserChats::getUser).collect(Collectors.toSet()))
                .build();
    }

    public List<ChatResponse> toList(List<Chat> chats) {
        return chats.stream().map(this::apply).collect(Collectors.toList());
    }
}
