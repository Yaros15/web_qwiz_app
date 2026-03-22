package com.example.web_qwiz_app.domain.service.impl;

import com.example.web_qwiz_app.domain.model.entity.Quiz;
import com.example.web_qwiz_app.domain.repository.QuizRepository;
import com.example.web_qwiz_app.domain.service.QuizService;
import com.example.web_qwiz_app.exception.ResourceNotFoundException;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTOResponse;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTORequest;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTOResponse;
import com.example.web_qwiz_app.web.dto.quiz.QuizMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    @Override
    public Page<PuzzleDTOResponse> getAllQuiz(Pageable pageable) {
        //TODO Реализовать постраничную выгрузку Всех Квизов
        return null;
    }

    private Quiz getQuizById(Long id){
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Квиз не найден"));
    }

    @Override
    public QuizDTOResponse findQuizById(Long id){
        return quizMapper.toResponse(getQuizById(id));
    }

    @Override
    public QuizDTOResponse createQuiz(QuizDTORequest request) {
        Quiz quiz = quizMapper.toEntity(request);

        Quiz quizSave = quizRepository.save(quiz);

        //TODO Добавить связь с Вопросом

        return quizMapper.toResponse(quizSave);
    }

    @Override
    public QuizDTOResponse updateQuiz(Long id, QuizDTORequest request) {
        Quiz quiz = getQuizById(id);

        quiz.setTitle(request.getTitle());
        quiz.setDescription(request.getDescription());

        Quiz quizUpdate = quizRepository.save(quiz);

        //TODO Добавить связь с Вопросом

        return quizMapper.toResponse(quizUpdate);

    }

    @Override
    public Boolean deleteQuiz(Long id) {

        if(quizRepository.existsById(id)) {
            quizRepository.delete(getQuizById(id));
            return true;
        }else{
            return false;
        }


    }
}
