package org.example.backend.service.impl;

import org.example.backend.exception.ChatNotFoundException;
import org.example.backend.exception.SaveChatException;
import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.Location;
import org.example.backend.model.entity.Message;
import org.example.backend.model.entity.User;
import org.example.backend.model.entity.enums.MessageType;
import org.example.backend.model.request.SaveChatRequest;
import org.example.backend.repository.ChatRepository;
import org.example.backend.service.ChatService;
import org.example.backend.service.LocationService;
import org.example.backend.service.MessageService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {
    private static final double SEARCH_RADIUS = 20d;
    private static final double ONE_DEGREE = 111d;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ImageService imageService;

    @Override
    public List<Chat> getUserChats() {
        return chatRepository.findByUsersIn(Set.of(userService.getUser()));
    }

    @Override
    public List<Chat> getChatsNearby() {
        User user = userService.getUser();
        Location location = user.getLocation();
        return getChatsNearby(location, SEARCH_RADIUS);
    }

    @Override
    @Transactional
    public Chat saveChat(SaveChatRequest request) {
        User currUser = userService.getUser();
        Location userLocation = currUser.getLocation();

        if (getChatsNearby(userLocation, 0.003).size() > 0)
            throw new SaveChatException("Chat nearby 3 meters already exists");
        Location newLocation = new Location(null, userLocation.getLatitude(), userLocation.getLongitude());
        Chat chat = new Chat(
                null,
                request.getName(),
                request.getDescription(),
                request.getPicture() == null ? currUser.getPicture() : imageService.uploadChatImage(request.getPicture(), null),
                currUser,
                Set.of(currUser),
                locationService.saveLocation(newLocation)
        );
        return chatRepository.save(chat);
    }

    @Override
    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

    private List<Chat> getChatsNearby(Location location, double radius) {
        double delta = radius / ONE_DEGREE;
        double lon1 = location.getLongitude() - delta, lon2 = location.getLongitude() + delta;
        double lat1 = location.getLatitude() - delta, lat2 = location.getLatitude() + delta;
        return chatRepository.findByLocation_LatitudeBetweenAndLocation_LongitudeBetween(lat1, lat2, lon1, lon2);
    }

    @Override
    public Chat getChat(Long id) {
        return chatRepository.findById(id).orElseThrow(() -> new ChatNotFoundException(id));
    }

    @Override
    @Transactional
    public Message joinChat(Long chatId) {
        Chat chat = getChat(chatId);
        User currUser = userService.getUser();
        chat.getUsers().add(currUser);
        Message joinMessage = messageService.saveMessage(new Message(
                null,
                "",
                currUser,
                chat,
                MessageType.JOINED,
                LocalDateTime.now()
        ));
        chatRepository.save(chat);
        return joinMessage;
    }

    @Override
    @Transactional
    public Message leaveChat(Long chatId) {
        Chat chat = getChat(chatId);
        User currUser = userService.getUser();
        chat.getUsers().remove(currUser);
        Message leftMessage = messageService.saveMessage(new Message(
                null,
                "",
                currUser,
                chat,
                MessageType.LEFT,
                LocalDateTime.now()
        ));
        chatRepository.save(chat);
        return leftMessage;
    }

    @Override
    public void deleteChat(Long chatId) {
        chatRepository.deleteById(chatId);
    }

    @Override
    public Chat updateChat(Long chatId, SaveChatRequest request) {
        Chat chat = getChat(chatId);
        chat.setDescription(request.getDescription());
        chat.setName(request.getName());
        if (request.getPicture() != null)
            chat.setPicture(imageService.uploadChatImage(request.getPicture(), chat.getPicture()));
        return saveChat(chat);
    }
}
