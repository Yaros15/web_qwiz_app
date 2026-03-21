package com.example.web_qwiz_app.web.controller;

import com.example.web_qwiz_app.domain.service.QuizService;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTORequest;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTOResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public void getAllQuiz(){
    }

    @GetMapping("{id}")
    public ResponseEntity<QuizDTOResponse> findQuizById(@PathVariable Long id){
        return ResponseEntity.ok(quizService.findQuizById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<QuizDTOResponse> createQuiz(@Valid @RequestBody QuizDTORequest request){}

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<QuizDTOResponse> updateQuiz(@PathVariable Long id, @Valid @RequestBody QuizDTORequest request){}

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id){

    }

}
