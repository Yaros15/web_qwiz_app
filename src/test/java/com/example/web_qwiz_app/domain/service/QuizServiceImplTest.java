package com.example.web_qwiz_app.domain.service;

import com.example.web_qwiz_app.domain.model.entity.Quiz;
import com.example.web_qwiz_app.domain.repository.AnswerRepository;
import com.example.web_qwiz_app.domain.repository.PuzzleRepository;
import com.example.web_qwiz_app.domain.repository.QuizRepository;
import com.example.web_qwiz_app.domain.service.impl.QuizServiceImpl;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTORequest;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTOResponse;
import com.example.web_qwiz_app.web.dto.quiz.QuizMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("QuizServiceImpl — юнит-тесты бизнес-логики")
public class QuizServiceImplTest {

    // фейковые объекты, которые настраиваем вручную -->
    @Mock
    private QuizRepository quizRepository;
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private PuzzleRepository puzzleRepository;
    @Mock
    private QuizMapper quizMapper;
    // фейковые объекты, которые настраиваем вручную <--

    // Тестируемый объект -->
    @InjectMocks
    private QuizServiceImpl quizService;
    // Тестируемый объект <--

    // Метод для создания Quiz объекта для тестов-->
    private Quiz createQuiz(Long id, String title, String description) {
        return Quiz.builder()
                .id(id)
                .title(title)
                .description(description)
                .build();
    }
    // Метод для создания Quiz объекта для тестов <--

    // Метод для создания QuizDTORequest объекта для тестов -->
    private QuizDTORequest createQuizRequest(String title, String description) {
        return QuizDTORequest.builder()
                .title(title)
                .description(description)
                .build();
    }
    // Метод для создания QuizDTORequest объекта для тестов <--

    // Метод для создания QuizDTOResponse объекта для тестов -->
    private QuizDTOResponse createQuizResponse(Long id, String title, String description) {
        return QuizDTOResponse.builder()
                .id(id)
                .title(title)
                .description(description)
                .build();
    }
    // Метод для создания QuizDTOResponse объекта для тестов <--

    // Вложенный класс для тестирования метода getAllQuiz() -->
    @Nested
    @DisplayName("getAllQuiz() — получение списка квизов с пагинацией")
    class GetAllQuizTests{

        @Test
        @DisplayName("должен вернуть пустую страницу, если в БД нет квизов")
        void shouldReturnEmptyPage_whenNoQuizzesInDatabase(){
            // Arrange Подготовка данных и настройка моков -->
            var pageable = PageRequest.of(0,10, Sort.by("title"));
            var emptyEntityPage = Page.<Quiz>empty(pageable);
            var emptyResponsePage = Page.<QuizDTOResponse>empty(pageable);

            given(quizRepository.findAll(pageable)).willReturn(emptyEntityPage);
            given(quizMapper.toResponsePage(emptyEntityPage)).willReturn(emptyResponsePage);
            // Arrange Подготовка данных и настройка моков <--

            // Act Вызов тестируемого метода -->
            Page<QuizDTOResponse> result = quizService.getAllQuiz(pageable);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result).isNotNull();

            assertThat(result.isEmpty()).isTrue();
            assertThat(result.getTotalElements()).isZero();

            // Верификация: убеждаемся, что методы моков были вызваны -->
            then(quizRepository).should().findAll(pageable);
            then(quizMapper).should().toResponsePage(emptyEntityPage);
            // Верификация: убеждаемся, что методы моков были вызваны <--
            // Assert Проверка результата <--
        }
    }
    // Вложенный класс для тестирования метода getAllQuiz() <--

    // Вложенный класс для тестирования метода findQuizById() -->
    // Вложенный класс для тестирования метода findQuizById() <--

    // Вложенный класс для тестирования метода createQuiz() -->
    // Вложенный класс для тестирования метода createQuiz() <--

    // Вложенный класс для тестирования метода updateQuiz() -->
    // Вложенный класс для тестирования метода updateQuiz() <--

    // Вложенный класс для тестирования метода deleteQuiz() -->
    // Вложенный класс для тестирования метода deleteQuiz() <--

}
