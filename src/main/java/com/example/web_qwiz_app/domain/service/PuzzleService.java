package com.example.web_qwiz_app.domain.service;

import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTORequest;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PuzzleService {

    Page<PuzzleDTOResponse> getAllPuzzle(Pageable pageable);

    PuzzleDTOResponse findPuzzleById(Long id);

    PuzzleDTOResponse createPuzzle(PuzzleDTORequest request);

    PuzzleDTOResponse updatePuzzle(Long id, PuzzleDTORequest request);

    Boolean deletePuzzle(Long id);

}
