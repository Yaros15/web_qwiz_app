package com.example.web_qwiz_app.web.dto.player;

import com.example.web_qwiz_app.domain.model.enums.Faculty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDTORequest {

    @NotBlank(message = "Укажите ваше имя")
    private String firstname;

    @NotBlank(message = "Выберите ваш факультет из Хогвартс")
    private Faculty faculty;

}
