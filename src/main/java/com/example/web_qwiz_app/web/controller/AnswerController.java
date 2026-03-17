package com.example.web_qwiz_app.web.controller;

import com.example.web_qwiz_app.domain.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping
    public void getAllAnswer(){
    }

    @GetMapping("{id}")
    public void findAnswerById(){}

    @PostMapping
    public void createAnswer(){}

    @PutMapping("{id}")
    public void updateAnswer(){}

    @DeleteMapping("{id}")
    public void deleteAnswer(){}

}
