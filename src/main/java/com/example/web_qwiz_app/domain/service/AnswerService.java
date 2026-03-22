package com.example.web_qwiz_app.domain.service;

import com.example.web_qwiz_app.web.dto.answer.AnswerDTORequest;
import com.example.web_qwiz_app.web.dto.answer.AnswerDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerService {

    Page<AnswerDTOResponse> getAllAnswer(Pageable pageable);

    AnswerDTOResponse findAnswerById(Long id);

    AnswerDTOResponse createAnswer(AnswerDTORequest request);

    AnswerDTOResponse updateAnswer(Long id, AnswerDTORequest request);

    Boolean deleteAnswer(Long id);

}
