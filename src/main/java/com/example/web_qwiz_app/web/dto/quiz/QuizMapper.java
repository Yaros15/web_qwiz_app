package com.example.web_qwiz_app.web.dto.quiz;

import com.example.web_qwiz_app.domain.model.entity.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public Page<QuizDTOResponse> toResponsePage (Page<Quiz> quizPage){
        List<QuizDTOResponse> quizList = quizPage
                .getContent()
                .stream()
                .map(this::toResponse)
                .toList();

        return new PageImpl<>(quizList, quizPage.getPageable(), quizPage.getTotalElements());
    }

}
