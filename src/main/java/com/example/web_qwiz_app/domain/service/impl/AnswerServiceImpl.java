package com.example.web_qwiz_app.domain.service.impl;

import com.example.web_qwiz_app.domain.model.entity.Answer;
import com.example.web_qwiz_app.domain.repository.AnswerRepository;
import com.example.web_qwiz_app.domain.service.AnswerService;
import com.example.web_qwiz_app.exception.ResourceNotFoundException;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTORequest;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTOResponse;
import com.example.web_qwiz_app.web.dto.answer.AnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    private Answer getAnswerById(Long id){
        return answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ответ не найден"));
    }

    @Override
    public AnswerDTOResponse findAnswerById(Long id) {
        return answerMapper.toResponse(getAnswerById(id));
    }

    @Override
    public AnswerDTOResponse createAnswer(AnswerDTORequest request) {
        Answer answer = answerMapper.toEntity(request);

        Answer answerSave = answerRepository.save(answer);

        return answerMapper.toResponse(answerSave);
    }

    @Override
    public AnswerDTOResponse updateAnswer(Long id, AnswerDTORequest request) {
        Answer answer = getAnswerById(id);
        answer.setAnswer(request.getAnswer());
        answer.setQuestCategory(request.getQuestCategory());

        Answer answerUpdate = answerRepository.save(answer);

        return answerMapper.toResponse(answerUpdate);
    }

    @Override
    public void deleteAnswer(Long id) {
        answerRepository.delete(getAnswerById(id));
    }
}
