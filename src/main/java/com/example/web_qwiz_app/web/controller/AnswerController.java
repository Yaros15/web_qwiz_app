package com.example.web_qwiz_app.web.controller;

import com.example.web_qwiz_app.domain.service.AnswerService;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTORequest;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTOResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<AnswerDTOResponse> findAnswerById(@PathVariable Long id){}

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AnswerDTOResponse> saveAnswer(@RequestBody AnswerDTORequest request){}

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AnswerDTOResponse> updateAnswer(@PathVariable Long id, @RequestBody AnswerDTORequest request){}

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long id){}

}
