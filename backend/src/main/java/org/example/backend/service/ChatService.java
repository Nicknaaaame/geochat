package org.example.backend.service;

import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.Location;

import java.util.List;

public interface ChatService {
    List<Chat> getChatsByLocation(Location location);
}
