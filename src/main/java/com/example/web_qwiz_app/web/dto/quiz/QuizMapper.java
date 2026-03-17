package com.example.web_qwiz_app.web.dto.quiz;

import com.example.web_qwiz_app.domain.model.entity.Quiz;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuizMapper {

    public QuizDTOResponse toResponse(Quiz quiz){
        if(quiz == null){
            return null;
        }

        return QuizDTOResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .build();

    }

    public Quiz toEntity(QuizDTORequest request){
        if(request == null){
            return null;
        }

        return Quiz.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

    }

}
