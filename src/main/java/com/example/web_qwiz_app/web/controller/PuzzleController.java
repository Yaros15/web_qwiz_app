package com.example.web_qwiz_app.web.controller;

import com.example.web_qwiz_app.domain.service.PuzzleService;
import lombok.RequiredArgsConstructor;
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
    public void findPuzzleById(){}

    @PostMapping
    public void createPuzzle(){}

    @PutMapping("{id}")
    public void updatePuzzle(){}

    @DeleteMapping("{id}")
    public void deletePuzzle(){}

}
