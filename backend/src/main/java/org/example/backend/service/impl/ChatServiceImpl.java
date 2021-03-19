package org.example.backend.service.impl;

import org.example.backend.exception.ChatNotFoundException;
import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.Location;
import org.example.backend.model.entity.User;
import org.example.backend.model.request.SaveChatRequest;
import org.example.backend.repository.ChatRepository;
import org.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {
    private static final double SEARCH_RADIUS = 5d;
    private static final double EARTH_RADIUS = 6317d;
    @Autowired
    private LocalChatService localChatService;
    @Autowired
    private PrivateChatService privateChatService;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;

    @Override
    public List<Object> getUserChats() {
        List<Object> userChats = new ArrayList<>();
        userChats.addAll(privateChatService.getUserPrivateChats());
        userChats.addAll(localChatService.getUserLocalChats());
        return userChats;
    }

    @Override
    public List<Chat> getChatsNearby() {
        Location location = userService.getUser().getLocation();
        double delta = SEARCH_RADIUS / EARTH_RADIUS;
        double lon1 = location.getLongitude() - delta, lon2 = location.getLongitude() + delta;
        double lat1 = location.getLatitude() - delta, lat2 = location.getLatitude() + delta;
        List<Chat> chatsNearby = chatRepository.findByLocation_LatitudeBetweenAndLocation_LongitudeBetween(lat1, lat2, lon1, lon2);
        return chatsNearby;
    }

    @Override
    @Transactional
    public Chat saveChat(SaveChatRequest request) {
        User currUser = userService.getUser();
        Location userLocation = currUser.getLocation();
        Location newLocation = new Location(null, userLocation.getLatitude(), userLocation.getLongitude());
        Chat chat = new Chat(
                null,
                request.getName(),
                request.getDescription(),
                request.getPicture().isEmpty() ? currUser.getPicture() : request.getPicture(),
                currUser,
                Set.of(currUser),
                locationService.saveLocation(newLocation)
        );
        return chatRepository.save(chat);
    }

    @Override
    public Chat getChat(Long id) {
        return chatRepository.findById(id).orElseThrow(() -> new ChatNotFoundException(id));
    }
}
