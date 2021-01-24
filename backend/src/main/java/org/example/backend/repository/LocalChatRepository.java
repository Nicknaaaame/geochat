package org.example.backend.repository;

import org.example.backend.model.entity.LocalChat;
import org.example.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface LocalChatRepository extends JpaRepository<LocalChat, Long> {
    List<LocalChat> findByLocation_LatitudeBetweenAndLocation_LongitudeBetween(Double lat1, Double lat2, Double lon1, Double lon2);
    List<LocalChat> findByUsersIn(Set<User> users);
}
