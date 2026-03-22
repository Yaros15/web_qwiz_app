package com.example.web_qwiz_app.web.dto.puzzle;

import com.example.web_qwiz_app.domain.model.enums.QuestCategory;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTORequest;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTORequest;
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
    private AnswerDTORequest answer;

    @NotNull(message = "Выберите категорию вопроса")
    private QuestCategory questCategory;

    @NotNull(message = "Укажите квиз")
    private QuizDTORequest quiz;

}
