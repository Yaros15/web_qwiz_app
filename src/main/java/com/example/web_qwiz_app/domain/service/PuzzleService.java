package com.example.web_qwiz_app.domain.service;

import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTORequest;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTOResponse;

public interface PuzzleService {

    //public void getAllQuiz();

    public PuzzleDTOResponse findPuzzleById(Long id);

    public PuzzleDTOResponse createPuzzle(PuzzleDTORequest request);

    public PuzzleDTOResponse updatePuzzle(Long id, PuzzleDTORequest request);

    public void deletePuzzle(Long id);

}
