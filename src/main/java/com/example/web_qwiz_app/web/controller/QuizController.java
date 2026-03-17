package com.example.web_qwiz_app.web.controller;

import com.example.web_qwiz_app.domain.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public void findQuizById(){}

    @PostMapping
    public void createQuiz(){}

    @PutMapping("{id}")
    public void updateQuiz(){}

    @DeleteMapping("{id}")
    public void deleteQuiz(){}

}
