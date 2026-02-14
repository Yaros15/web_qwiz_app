package com.example.web_qwiz_app.web.dto.player;

import com.example.web_qwiz_app.domain.model.entity.Player;
import com.example.web_qwiz_app.web.dto.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerMapper {

    private final UserMapper userMapper;

    public PlayerDTOResponse response(Player player){
        if(player == null){
            return null;
        }

        PlayerDTOResponse playerResponse = PlayerDTOResponse.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .faculty(player.getFaculty())
                .userDTOResponse(userMapper.toSimpleResponse(player.getUser()))
                .build();

        return  playerResponse;
    }

    public Player toEntity(PlayerDTORequest request){

        if(request == null){
            return null;
        }

        return Player.builder()
                .firstName(request.getFirstname())
                .faculty(request.getFaculty())
                .build();

    }

    public void updateEntity(PlayerDTORequestUpdate request, Player player){

        if(request == null || player == null){
            return;
        }

        if(request.getFirstname() != null){
            player.setFirstName(request.getFirstname());
        }

        if(request.getFaculty() != null){
            player.setFaculty(request.getFaculty());
        }

    }

}