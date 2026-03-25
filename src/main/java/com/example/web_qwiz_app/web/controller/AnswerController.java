package com.example.web_qwiz_app.web.controller;

import com.example.web_qwiz_app.domain.service.AnswerService;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTORequest;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTOResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping
    public ResponseEntity<Page<AnswerDTOResponse>> getAllAnswer(@PageableDefault(size = 10, sort = "answer", direction = Sort.Direction.ASC)
                                 Pageable pageable){
        Page<AnswerDTOResponse> answerDTOResponsePage = answerService.getAllAnswer(pageable);
        return ResponseEntity.ok(answerDTOResponsePage);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnswerDTOResponse> findAnswerById(@PathVariable Long id){
        return ResponseEntity.ok(answerService.findAnswerById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AnswerDTOResponse> saveAnswer(@Valid @RequestBody AnswerDTORequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(answerService.createAnswer(request));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AnswerDTOResponse> updateAnswer(@PathVariable Long id, @Valid @RequestBody AnswerDTORequest request){
        return ResponseEntity.ok(answerService.updateAnswer(id,request));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long id){
        if(answerService.deleteAnswer(id)) {
            return ResponseEntity.ok("Ответ удален");
        }else{
            return ResponseEntity.badRequest().body(String.format("Ответ с номером ид: %d - нет в базе данных", id));
        }
    }

}
