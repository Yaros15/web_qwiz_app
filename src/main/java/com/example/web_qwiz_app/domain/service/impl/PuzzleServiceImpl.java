package com.example.web_qwiz_app.domain.service.impl;

import com.example.web_qwiz_app.domain.service.PuzzleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PuzzleServiceImpl implements PuzzleService {
}
