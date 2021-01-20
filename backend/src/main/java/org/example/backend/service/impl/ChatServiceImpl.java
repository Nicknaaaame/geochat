package org.example.backend.service.impl;

import org.example.backend.exception.UserNotFoundException;
import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.Location;
import org.example.backend.model.request.SaveChatRequest;
import org.example.backend.repository.ChatRepository;
import org.example.backend.service.ChatService;
import org.example.backend.service.LocationService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;

    @Override
    public Chat saveChat(SaveChatRequest request) {
        Location location = userService.getUser().getLocation();
        Location newLocation = new Location(null, location.getLatitude(), location.getLongitude());
        return chatRepository.save(new Chat(
                null,
                request.getName(),
                userService.getUser(),
                request.getUsers().stream()
                        .map(id -> userService.getUserById(id).orElseThrow(() -> new UserNotFoundException(id)))
                        .collect(Collectors.toSet()),
                locationService.saveLocation(newLocation)));
    }

    @Override
    public List<Chat> getChatsAround() {
        Location location = userService.getUser().getLocation();
        double delta = 5.0d / 6317d;
        double lon1 = location.getLongitude() - delta, lon2 = location.getLongitude() + delta;
        double lat1 = location.getLatitude() - delta, lat2 = location.getLatitude() + delta;
        return chatRepository.findByLocation_LatitudeBetweenAndLocation_LongitudeBetween(lat1, lat2, lon1, lon2);
    }
}
