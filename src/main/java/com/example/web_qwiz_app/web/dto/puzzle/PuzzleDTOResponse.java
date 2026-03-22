package com.example.web_qwiz_app.web.dto.puzzle;

import com.example.web_qwiz_app.domain.model.entity.Answer;
import com.example.web_qwiz_app.domain.model.enums.QuestCategory;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTOResponse;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTORequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PuzzleDTOResponse {

    private Long id;
    private String question;
    private AnswerDTOResponse answer;
    private QuestCategory questCategory;
    private QuizDTORequest quiz;

}