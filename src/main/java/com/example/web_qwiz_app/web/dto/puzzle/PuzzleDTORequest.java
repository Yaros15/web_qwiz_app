package com.example.web_qwiz_app.web.dto.puzzle;

import com.example.web_qwiz_app.domain.model.entity.Answer;
import com.example.web_qwiz_app.domain.model.enums.QuestCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PuzzleDTORequest {

    @NotNull(message = "Введите вопрос")
    private String question;

    @NotNull(message = "Укажите ответ")
    private Answer answer;

    @NotNull(message = "Выберите категорию вопроса")
    private QuestCategory questCategory;

}
