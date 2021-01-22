package org.example.backend.service.impl;

import org.example.backend.exception.UserNotFoundException;
import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.Location;
import org.example.backend.model.entity.User;
import org.example.backend.model.request.SaveChatRequest;
import org.example.backend.repository.ChatRepository;
import org.example.backend.service.ChatService;
import org.example.backend.service.LocationService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {
    private static final double SEARCH_RADIUS = 5d;
    private static final double EARTH_RADIUS = 6317d;

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;

    @Override
    public Chat saveChat(SaveChatRequest request) {
        User currentUser = userService.getUser();
        Location location = currentUser.getLocation();
        Location newLocation = new Location(null, location.getLatitude(), location.getLongitude());
        Set<User> users = request.getUsers().stream()
                .map(id -> userService.getUserById(id).orElseThrow(() -> new UserNotFoundException(id)))
                .collect(Collectors.toSet());
        users.add(currentUser);
        return chatRepository.save(new Chat(
                null,
                request.getName(),
                currentUser,
                users,
                locationService.saveLocation(newLocation)));
    }

    @Override
    public List<Chat> getChatsAround() {
        Location location = userService.getUser().getLocation();
        double delta = SEARCH_RADIUS / EARTH_RADIUS;
        double lon1 = location.getLongitude() - delta, lon2 = location.getLongitude() + delta;
        double lat1 = location.getLatitude() - delta, lat2 = location.getLatitude() + delta;
        List<Chat> userChats = getUserChats();
        List<Chat> chatsAround = chatRepository.findByLocation_LatitudeBetweenAndLocation_LongitudeBetween(lat1, lat2, lon1, lon2);
        chatsAround.removeAll(userChats);
        return chatsAround;
    }

    @Override
    public List<Chat> getUserChats() {
        return chatRepository.findByUsersIn(Set.of(userService.getUser()));
    }
}
