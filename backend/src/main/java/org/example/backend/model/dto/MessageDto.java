package org.example.backend.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.model.entity.enums.MessageType;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MessageDto {
    private Long id;
    private String text;
    private UserDto sender;
    private MessageType messageType;
    private LocalDateTime creationDate;
}
