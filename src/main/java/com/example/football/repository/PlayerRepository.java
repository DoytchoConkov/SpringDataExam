package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "SELECT p FROM Player AS p WHERE p.birthdate BETWEEN :startDate AND :endDate ORDER BY p.stat.shooting DESC ,p.stat.passing DESC ,p.stat.endurance DESC ,p.lastName")
    List<Player> getAllPlayerOrdered(@Param(value = "startDate") LocalDate startDate, @Param(value = "endDate") LocalDate endDate);

    Optional<Player> findByEmail(String email);
}
