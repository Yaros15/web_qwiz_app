package com.example.web_qwiz_app.web.dto.answer;

import com.example.web_qwiz_app.domain.model.enums.QuestCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTOResponse {

    private Long id;

    private String answer;

    private QuestCategory questCategory;

}