package com.example.web_qwiz_app.web.controller;

import com.example.web_qwiz_app.domain.service.PuzzleService;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTORequest;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTOResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        //TODO Реализовать постраничную выгрузку Всех Вопросов
    }

    @GetMapping("{id}")
    public ResponseEntity<PuzzleDTOResponse> findPuzzleById(@PathVariable Long id){
        return ResponseEntity.ok(puzzleService.findPuzzleById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PuzzleDTOResponse> savePuzzle(@Valid @RequestBody PuzzleDTORequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(puzzleService.createPuzzle(request));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PuzzleDTOResponse> updatePuzzle(@PathVariable Long id, @Valid @RequestBody PuzzleDTORequest request){
        return ResponseEntity.ok(puzzleService.updatePuzzle(id,request));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deletePuzzle(@PathVariable Long id){
        if(puzzleService.deletePuzzle(id)) {
            return ResponseEntity.ok("Вопрос удален");
        }else{
            return ResponseEntity.badRequest().body(String.format("Вопрос с номером ид: %d - нет в базе данных", id));
        }
    }

}
