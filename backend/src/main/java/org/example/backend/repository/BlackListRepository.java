package org.example.backend.repository;

import org.example.backend.model.entity.BlackList;
import org.example.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    Optional<BlackList> findByBlockerAndBlocked(User blocker, User blocked);

    List<BlackList> findAllByBlocker(User user);

    List<BlackList> findAllByBlocked(User user);
}
