package com.example.web_qwiz_app.web.dto.answer;

import com.example.web_qwiz_app.domain.model.enums.QuestCategory;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTOResponse {

    private Long id;

    private String answer;

    private QuestCategory questCategory;

    private List<PuzzleDTOResponse> questions;

}