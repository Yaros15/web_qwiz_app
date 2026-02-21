package com.example.web_qwiz_app.domain.service;

import com.example.web_qwiz_app.web.dto.player.PlayerDTORequest;
import com.example.web_qwiz_app.web.dto.player.PlayerDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    PlayerDTOResponse createPlayer(Long userId, PlayerDTORequest request);

    Page<PlayerDTOResponse.Simple> getAllPlayer(Pageable pageable);

    PlayerDTOResponse getPlayerById(Long id);

    PlayerDTOResponse getCurrentPlayer();

    Long getUserIdByPlayerId(Long playerId);

    Page<PlayerDTOResponse.Simple> searchPlayer(String firstName);

}
