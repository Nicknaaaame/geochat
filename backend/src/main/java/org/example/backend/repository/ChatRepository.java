package org.example.backend.repository;

import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByLocation_LatitudeBetweenAndLocation_LongitudeBetween(Double lat1, Double lat2, Double lon1, Double lon2);

    @Query("SELECT uc.chat FROM UserChats uc INNER JOIN uc.chat WHERE uc.user = :user")
    List<Chat> findUserChats(User user);
}
