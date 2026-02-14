package com.example.web_qwiz_app.web.dto.player;

import com.example.web_qwiz_app.domain.model.enums.Faculty;
import com.example.web_qwiz_app.web.dto.user.UserDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDTOResponse {

    private Long id;
    private String firstName;
    private Faculty faculty;
    private UserDTOResponse.Simple userDTO;

}
