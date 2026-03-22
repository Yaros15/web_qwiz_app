package com.example.web_qwiz_app.domain.service.impl;

import com.example.web_qwiz_app.domain.model.entity.Puzzle;
import com.example.web_qwiz_app.domain.repository.PuzzleRepository;
import com.example.web_qwiz_app.domain.service.PuzzleService;
import com.example.web_qwiz_app.exception.ResourceNotFoundException;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTORequest;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTOResponse;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PuzzleServiceImpl implements PuzzleService {

    private final PuzzleRepository puzzleRepository;
    private final PuzzleMapper puzzleMapper;

    private Puzzle getPuzzleById(Long id){
        return puzzleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Вопрос не найден"));
    }

    @Override
    public Page<PuzzleDTOResponse> getAllPuzzle(Pageable pageable) {
        //TODO Реализовать постраничную выгрузку Всех Вопросов
        return null;
    }

    @Override
    public PuzzleDTOResponse findPuzzleById(Long id) {
        return puzzleMapper.toResponse(getPuzzleById(id));
    }

    @Override
    public PuzzleDTOResponse createPuzzle(PuzzleDTORequest request) {
        Puzzle puzzle = puzzleMapper.toEntity(request);

        Puzzle puzzleSave = puzzleRepository.save(puzzle);

        //TODO Добавить связь с Квизом
        //TODO Добавить связь с Ответом

        return puzzleMapper.toResponse(puzzleSave);
    }

    @Override
    public PuzzleDTOResponse updatePuzzle(Long id, PuzzleDTORequest request) {
        Puzzle puzzle = getPuzzleById(id);
        puzzle.setQuestion(request.getQuestion());
        puzzle.setAnswer(request.getAnswer());
        puzzle.setQuestCategory(request.getQuestCategory());

        Puzzle puzzleUpdate = puzzleRepository.save(puzzle);

        //TODO Добавить связь с Квизом

        return puzzleMapper.toResponse(puzzleUpdate);

    }

    @Override
    public Boolean deletePuzzle(Long id) {

        if(puzzleRepository.existsById(id)) {
            puzzleRepository.delete(getPuzzleById(id));
            return true;
        }else{
            return false;
        }

    }
}
