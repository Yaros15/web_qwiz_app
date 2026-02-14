package com.example.web_qwiz_app.web.dto.player;

import com.example.web_qwiz_app.domain.model.enums.Faculty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDTORequestUpdate {

    private String firstname;

    private Faculty faculty;

}