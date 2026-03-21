package com.example.web_qwiz_app.web.controller;

import com.example.web_qwiz_app.domain.service.PuzzleService;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTORequest;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTOResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/puzzle")
@RequiredArgsConstructor
public class PuzzleController {

    private final PuzzleService puzzleService;

    @GetMapping
    public void getAllPuzzle(){
    }

    @GetMapping("{id}")
    public ResponseEntity<PuzzleDTOResponse> findPuzzleById(@PathVariable Long id){}

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PuzzleDTOResponse> savePuzzle(@RequestBody PuzzleDTORequest request){}

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PuzzleDTOResponse> updatePuzzle(@PathVariable Long id, @RequestBody PuzzleDTORequest request){}

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deletePuzzle(@PathVariable Long id){}

}
