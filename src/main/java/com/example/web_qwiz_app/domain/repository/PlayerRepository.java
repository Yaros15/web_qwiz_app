package com.example.web_qwiz_app.domain.repository;

import com.example.web_qwiz_app.domain.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByUser_Username(String username);

}
