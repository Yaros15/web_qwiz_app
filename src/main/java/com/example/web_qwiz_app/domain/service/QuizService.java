package com.example.web_qwiz_app.domain.service;

import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTOResponse;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTORequest;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {

    Page<PuzzleDTOResponse> getAllQuiz(Pageable pageable);

    QuizDTOResponse findQuizById(Long id);

    QuizDTOResponse createQuiz(QuizDTORequest request);

    QuizDTOResponse updateQuiz(Long id, QuizDTORequest request);

    Boolean deleteQuiz(Long id);

}
