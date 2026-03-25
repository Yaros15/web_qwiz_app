package com.example.web_qwiz_app.domain.service.impl;

import com.example.web_qwiz_app.domain.model.entity.Answer;
import com.example.web_qwiz_app.domain.repository.AnswerRepository;
import com.example.web_qwiz_app.domain.service.AnswerService;
import com.example.web_qwiz_app.exception.ResourceNotFoundException;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTORequest;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTOResponse;
import com.example.web_qwiz_app.web.dto.answer.AnswerMapper;
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
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Override
    public Page<AnswerDTOResponse> getAllAnswer(Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findAll(pageable);
        return answerMapper.toResponsePage(answerPage);
    }

    private Answer getAnswerById(Long id){
        return answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ответ не найден"));
    }

    @Override
    public AnswerDTOResponse findAnswerById(Long id) {
        return answerMapper.toResponse(getAnswerById(id));
    }

    @Override
    @Transactional
    public AnswerDTOResponse createAnswer(AnswerDTORequest request) {
        Answer answer = answerMapper.toEntity(request);

        Answer answerSave = answerRepository.save(answer);

        //TODO Добавить связь с Вопросом

        return answerMapper.toResponse(answerSave);
    }

    @Override
    @Transactional
    public AnswerDTOResponse updateAnswer(Long id, AnswerDTORequest request) {
        Answer answer = getAnswerById(id);
        answer.setAnswer(request.getAnswer());
        answer.setQuestCategory(request.getQuestCategory());

        Answer answerUpdate = answerRepository.save(answer);

        //TODO Добавить связь с Вопросом

        return answerMapper.toResponse(answerUpdate);
    }

    @Override
    public Boolean deleteAnswer(Long id) {
        if(answerRepository.existsById(id)) {
            answerRepository.delete(getAnswerById(id));
            return true;
        }else{
            return false;
        }
    }
}
