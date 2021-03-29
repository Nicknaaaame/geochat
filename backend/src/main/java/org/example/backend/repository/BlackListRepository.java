package org.example.backend.repository;

import org.example.backend.model.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    void deleteByUser_IdAndChat_Id(Long userId, Long chatId);

    List<BlackList> findAllByChat_Id(Long chatId);

    Boolean existsByUser_IdAndChat_Id(Long userId, Long chatId);
}
