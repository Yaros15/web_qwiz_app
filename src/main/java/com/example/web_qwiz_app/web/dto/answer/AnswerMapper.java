package com.example.web_qwiz_app.web.dto.answer;

import com.example.web_qwiz_app.domain.model.entity.Answer;
import org.springframework.stereotype.Component;

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

    public void updateAnswer(AnswerDTORequest request, Answer answer){
        if(request == null || answer == null){
            return;
        }

        answer.setAnswer(request.getAnswer());
        answer.setQuestCategory(request.getQuestCategory());
    }

}