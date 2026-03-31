package com.example.web_qwiz_app.web.dto.quiz;

import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTOResponse {
    private Long id;
    private String title;
    private String description;
    private List<PuzzleDTOResponse> questions;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class QuizShort{
        private Long id;
        private String title;
        private String description;
    }
}
