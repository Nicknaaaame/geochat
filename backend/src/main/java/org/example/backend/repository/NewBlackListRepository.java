package org.example.backend.repository;

import org.example.backend.model.entity.NewBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewBlackListRepository extends JpaRepository<NewBlackList, Long> {
    void deleteByUser_IdAndChat_Id(Long userId, Long chatId);

    List<NewBlackList> findAllByChat_Id(Long chatId);

    Boolean existsByUser_IdAndChat_Id(Long userId, Long chatId);
}
