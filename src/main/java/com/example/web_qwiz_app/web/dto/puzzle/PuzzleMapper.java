package com.example.web_qwiz_app.web.dto.puzzle;

import com.example.web_qwiz_app.domain.model.entity.Puzzle;
import com.example.web_qwiz_app.web.dto.answer.AnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PuzzleMapper {

    private final AnswerMapper answerMapper;

    public PuzzleDTOResponse toResponse (Puzzle puzzle){
        if(puzzle == null){
            return null;
        }

        PuzzleDTOResponse response = PuzzleDTOResponse.builder()
                .id(puzzle.getId())
                .question(puzzle.getQuestion())
                .answer(answerMapper.toResponse(puzzle.getAnswer()))
                .questCategory(puzzle.getQuestCategory())
                .build();

        return response;

    }

    public Puzzle toEntity (PuzzleDTORequest request){
        if(request == null){
            return null;
        }

        Puzzle puzzle = Puzzle.builder()
                .question(request.getQuestion())
                .questCategory(request.getQuestCategory())
                .answer(request.getAnswer())
                .build();

        return puzzle;
    }

}