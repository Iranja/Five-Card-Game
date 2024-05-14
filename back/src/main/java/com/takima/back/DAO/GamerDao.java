package com.takima.back.DAO;

import com.takima.back.models.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamerDao extends JpaRepository<Gamer, Long> {
    Optional<Gamer> findByUsername(String username);
    Optional<Gamer> findByEmail(String email);
}

