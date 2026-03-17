package com.example.web_qwiz_app.web.dto.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuizDTORequest {

    @NotBlank(message = "Название квиза обязательно")
    @Size(min = 3, max = 100, message = "Название должно содержать от 3 до 100 символов")
    private String title;

    @Size(max = 1000, message = "Описание не должно превышать 1000 символов")
    private String description;

}
