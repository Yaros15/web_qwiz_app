package com.example.web_qwiz_app.domain.service;

import com.example.web_qwiz_app.web.dto.answer.AnswerDTORequest;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTOResponse;

public interface AnswerService {

    //public void getAllAnswer();

    public AnswerDTOResponse findAnswerById(Long id);

    public AnswerDTOResponse createAnswer(AnswerDTORequest request);

    public AnswerDTOResponse updateAnswer(Long id, AnswerDTORequest request);

    public void deleteAnswer(Long id);

}
