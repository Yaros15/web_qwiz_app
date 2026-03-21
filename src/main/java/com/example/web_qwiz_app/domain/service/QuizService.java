package com.example.web_qwiz_app.domain.service;

import com.example.web_qwiz_app.web.dto.quiz.QuizDTORequest;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTOResponse;

public interface QuizService {

    //public void getAllQuiz();

    public QuizDTOResponse findQuizById(Long id);

    public QuizDTOResponse createQuiz(QuizDTORequest request);

    public QuizDTOResponse updateQuiz(Long id, QuizDTORequest request);

    public void deleteQuiz(Long id);

}
