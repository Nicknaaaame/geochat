package org.example.backend.service.impl;

import org.example.backend.exception.LocalChatNotFoundException;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.model.entity.LocalChat;
import org.example.backend.model.entity.Location;
import org.example.backend.model.entity.User;
import org.example.backend.model.request.JoinLocalChatRequest;
import org.example.backend.model.request.SaveLocalChatRequest;
import org.example.backend.repository.LocalChatRepository;
import org.example.backend.service.LocalChatService;
import org.example.backend.service.LocationService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocalChatServiceImpl implements LocalChatService {
    private static final double SEARCH_RADIUS = 5d;
    private static final double EARTH_RADIUS = 6317d;

    @Autowired
    private LocalChatRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;

    @Override
    public LocalChat saveLocalChat(SaveLocalChatRequest request) {
        User currentUser = userService.getUser();
        Location location = currentUser.getLocation();
        Location newLocation = new Location(null, location.getLatitude(), location.getLongitude());
        Set<User> users = request.getUsers().stream()
                .map(id -> userService.getUserById(id).orElseThrow(() -> new UserNotFoundException(id)))
                .collect(Collectors.toSet());
        users.add(currentUser);
        return repository.save(new LocalChat(
                null,
                request.getName(),
                currentUser,
                users,
                locationService.saveLocation(newLocation)));
    }

    @Override
    public LocalChat saveLocalChat(LocalChat localChat) {
        return repository.save(localChat);
    }

    @Override
    public LocalChat joinLocalChat(JoinLocalChatRequest request) {
        Long id = request.getId();
        LocalChat localChat = getLocalChat(id).orElseThrow(() -> new LocalChatNotFoundException(id));
        localChat.getUsers().add(userService.getUser());
        return saveLocalChat(localChat);
    }

    @Override
    public Optional<LocalChat> getLocalChat(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<LocalChat> getLocalChatsAround() {
        Location location = userService.getUser().getLocation();
        double delta = SEARCH_RADIUS / EARTH_RADIUS;
        double lon1 = location.getLongitude() - delta, lon2 = location.getLongitude() + delta;
        double lat1 = location.getLatitude() - delta, lat2 = location.getLatitude() + delta;
        List<LocalChat> userLocalChats = getUserLocalChats();
        List<LocalChat> chatsAround = repository.findByLocation_LatitudeBetweenAndLocation_LongitudeBetween(lat1, lat2, lon1, lon2);
        chatsAround.removeAll(userLocalChats);
        return chatsAround;
    }

    @Override
    public List<LocalChat> getUserLocalChats() {
        return repository.findByUsersIn(Set.of(userService.getUser()));
    }
}
