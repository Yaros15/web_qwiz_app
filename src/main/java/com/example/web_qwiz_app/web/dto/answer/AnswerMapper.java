package com.example.web_qwiz_app.web.dto.answer;

import com.example.web_qwiz_app.domain.model.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnswerMapper {

    public AnswerDTOResponse toResponse(Answer answer){
        if(answer == null){
            return null;
        }

        return AnswerDTOResponse.builder()
                .id(answer.getId())
                .answer(answer.getAnswer())
                .questCategory(answer.getQuestCategory())
                .build();

    }

    public Answer toEntity (AnswerDTORequest request){
        if(request == null){
            return null;
        }

        return Answer.builder()
                .answer(request.getAnswer())
                .questCategory(request.getQuestCategory())
                .build();

    }

    public Page<AnswerDTOResponse> toResponsePage (Page<Answer> answerPage){
        List<AnswerDTOResponse> answerList = answerPage
                .getContent()
                .stream()
                .map(this::toResponse)
                .toList();

        return new PageImpl<>(answerList, answerPage.getPageable(), answerPage.getTotalElements());
    }

}