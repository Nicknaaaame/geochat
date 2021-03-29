package org.example.backend.converter;

import org.example.backend.model.dto.MessageDto;
import org.example.backend.model.entity.Message;

import java.util.function.Function;

public class MessageDtoConverter implements Function<Message, MessageDto> {
    @Override
    public MessageDto apply(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .text(message.getText())
                .creationDate(message.getCreationDate())
                .messageType(message.getMessageType())
                .sender(new UserDtoConverter().apply(message.getSender()))
                .build();
    }
}
