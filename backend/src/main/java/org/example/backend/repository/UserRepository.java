package org.example.backend.repository;

import org.example.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    List<User> findByLocation_LatitudeBetweenAndLocation_LongitudeBetween(Double lat1, Double lat2, Double lon1, Double lon2);
}
