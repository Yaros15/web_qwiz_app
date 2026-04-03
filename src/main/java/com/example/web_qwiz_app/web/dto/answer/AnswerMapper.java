package com.example.web_qwiz_app.web.dto.answer;

import com.example.web_qwiz_app.domain.model.entity.Answer;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnswerMapper {

    private final PuzzleMapper puzzleMapper;

    public AnswerDTOResponse toResponse(Answer answer){
        if(answer == null){
            return null;
        }

        return AnswerDTOResponse.builder()
                .id(answer.getId())
                .answer(answer.getAnswer())
                .questCategory(answer.getQuestCategory())
                .questions(puzzleMapper.toResponseList(answer.getPuzzles()))
                .build();

    }

    public AnswerDTOResponse.AnswerShort toResponseShort(Answer answer){
        if(answer == null){
            return null;
        }

        return AnswerDTOResponse.AnswerShort.builder()
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
                .puzzles(puzzleMapper.toEntityList(request.getQuestions()))
                .build();

    }

}