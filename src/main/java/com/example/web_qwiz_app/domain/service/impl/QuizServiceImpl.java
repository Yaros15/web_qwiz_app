package com.example.web_qwiz_app.domain.service.impl;

import com.example.web_qwiz_app.domain.model.entity.Answer;
import com.example.web_qwiz_app.domain.model.entity.Puzzle;
import com.example.web_qwiz_app.domain.model.entity.Quiz;
import com.example.web_qwiz_app.domain.repository.AnswerRepository;
import com.example.web_qwiz_app.domain.repository.PuzzleRepository;
import com.example.web_qwiz_app.domain.repository.QuizRepository;
import com.example.web_qwiz_app.domain.service.QuizService;
import com.example.web_qwiz_app.exception.ResourceNotFoundException;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTORequest;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTORequest;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTOResponse;
import com.example.web_qwiz_app.web.dto.quiz.QuizMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;
    private final PuzzleRepository puzzleRepository;
    private final QuizMapper quizMapper;

    @Override
    public Page<QuizDTOResponse> getAllQuiz(Pageable pageable) {
        Page<Quiz> quizPage = quizRepository.findAll(pageable);
        return quizMapper.toResponsePage(quizPage);
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
    @Transactional
    public QuizDTOResponse createQuiz(QuizDTORequest request) {
        Quiz quiz = quizMapper.toEntity(request);

        if(request.getQuestions() != null){
            for(PuzzleDTORequest puzzleRequest : request.getQuestions()){

                Answer answer = Answer.builder()
                        .answer("") //TODO Добавить работу с ответом
                        .questCategory(puzzleRequest.getQuestCategory())
                        .build();

                answerRepository.save(answer);

                Puzzle puzzle = Puzzle.builder()
                        .question(puzzleRequest.getQuestion())
                        .answer(answer)
                        .questCategory(puzzleRequest.getQuestCategory())
                        .build();

                puzzleRepository.save(puzzle);

                quiz.addPuzzle(puzzle);
            }
        }

        Quiz quizSave = quizRepository.save(quiz);

        return quizMapper.toResponse(quizSave);
    }

    @Override
    @Transactional
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
