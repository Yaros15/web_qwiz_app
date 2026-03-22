package com.example.web_qwiz_app.web.dto.answer;

import com.example.web_qwiz_app.domain.model.enums.QuestCategory;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTORequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTORequest {

    @NotNull(message = "Укажите ответ")
    private String answer;

    @NotNull(message = "Выберите категорию ответа")
    private QuestCategory questCategory;

    private List<PuzzleDTORequest> questions;

}