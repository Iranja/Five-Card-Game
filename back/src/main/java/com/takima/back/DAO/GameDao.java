package com.takima.back.DAO;

import com.takima.back.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameDao extends JpaRepository<Game, Long> {

    List<Game> findByUserId(Long userId);
    List<Game> findAllByUserIdOrderById(Long id);
}
