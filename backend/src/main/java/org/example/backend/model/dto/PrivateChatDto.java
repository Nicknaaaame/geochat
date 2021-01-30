package org.example.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.backend.model.entity.User;

@Data
@AllArgsConstructor
public class PrivateChatDto {
    private Long id;
    private User user;
}
