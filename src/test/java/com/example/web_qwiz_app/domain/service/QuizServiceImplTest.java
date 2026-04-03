package com.example.web_qwiz_app.domain.service;

import com.example.web_qwiz_app.domain.model.entity.Answer;
import com.example.web_qwiz_app.domain.model.entity.Puzzle;
import com.example.web_qwiz_app.domain.model.entity.Quiz;
import com.example.web_qwiz_app.domain.model.enums.QuestCategory;
import com.example.web_qwiz_app.domain.repository.AnswerRepository;
import com.example.web_qwiz_app.domain.repository.PuzzleRepository;
import com.example.web_qwiz_app.domain.repository.QuizRepository;
import com.example.web_qwiz_app.domain.service.impl.QuizServiceImpl;
import com.example.web_qwiz_app.exception.ResourceNotFoundException;
import com.example.web_qwiz_app.web.dto.puzzle.PuzzleDTORequest;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTORequest;
import com.example.web_qwiz_app.web.dto.quiz.QuizDTOResponse;
import com.example.web_qwiz_app.web.dto.quiz.QuizMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

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
            // Arrange Подготовка данных -->
            var pageable = PageRequest.of(0,10, Sort.by("title"));
            var emptyEntityPage = Page.<Quiz>empty(pageable);
            var emptyResponsePage = Page.<QuizDTOResponse>empty(pageable);

                        // настройка моков -->
            //Когда будет вызван метод findAll(pageable) у объекта quizRepository, тест передаст объект emptyEntityPage
            given(quizRepository.findAll(pageable)).willReturn(emptyEntityPage);
            //Когда будет вызван метод .toResponsePage(emptyEntityPage) у объекта quizRepository, тест передаст объект emptyResponsePage
            given(quizMapper.toResponsePage(emptyEntityPage)).willReturn(emptyResponsePage);
                        // настройка моков <--
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            Page<QuizDTOResponse> result = quizService.getAllQuiz(pageable);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            //Утверждаю что объект result не равен нулю (То есть объект по ссылочной переменной создан)
            assertThat(result).isNotNull();

            //Утверждаю что объект result - пустой
            assertThat(result.isEmpty()).isTrue();

            //Утверждаю, что в объект result всего элементов = 0
            assertThat(result.getTotalElements()).isZero();

            // Верификация: убеждаемся, что методы моков были вызваны -->
            // У объекта quizRepository был вызван метод findAll(pageable)
            then(quizRepository).should().findAll(pageable);
            // У объекта quizMapper был вызван метод toResponsePage(emptyEntityPage)
            then(quizMapper).should().toResponsePage(emptyEntityPage);
            // Верификация: убеждаемся, что методы моков были вызваны <--
            // Assert Проверка результата <--
        }

        @Test
        @DisplayName("должен вернуть страницу с квизами, если они есть")
        void shouldReturnQuizPage_whenQuizzesExist(){
            // Arrange Подготовка данных -->
            var pageable = PageRequest.of(0,10);

            var quiz = Quiz.builder()
                    .id(1L)
                    .title("Test Quiz")
                    .description("Test Quiz Description")
                    .build();

            var quizPage = new PageImpl<>(List.of(quiz), pageable, 1);

            var response = QuizDTOResponse.builder()
                    .id(1L)
                    .title("Test Quiz")
                    .description("Test Quiz Description")
                    .build();
            var responsePage = new PageImpl<>(List.of(response), pageable, 1);

            // настройка моков -->
            given(quizRepository.findAll(pageable)).willReturn(quizPage);
            given(quizMapper.toResponsePage(quizPage)).willReturn(responsePage);
            // настройка моков <--
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            var result = quizService.getAllQuiz(pageable);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result).isNotNull();

            // Проверяем размер контента (должен быть 1 элемент)
            assertThat(result.getContent()).hasSize(1);

            assertThat(result.getContent().stream().findFirst().get().getTitle())
                    .isEqualTo("Test Quiz");

            // Верификация: убеждаемся, что методы моков были вызваны -->
            then(quizRepository).should().findAll(pageable);
            then(quizMapper).should().toResponsePage(quizPage);
            // Верификация: убеждаемся, что методы моков были вызваны <--
            // Assert Проверка результата <--
        }



    }
    // Вложенный класс для тестирования метода getAllQuiz() <--

    // Вложенный класс для тестирования метода findQuizById() -->
    @Nested
    @DisplayName("findQuizById() — поиск квиза по ID")
    class FindQuizByIdTests {
        @Test
        @DisplayName("должен вернуть QuizDTOResponse, если квиз найден")
        void shouldReturnQuizResponse_whenQuizExists() {
            // Arrange Подготовка данных -->
            Long quizId = 1L;
            var quiz = createQuiz(quizId, "Found Quiz", "Found Desc");
            var response = createQuizResponse(quizId, "Found Quiz", "Found Desc");

            given(quizRepository.findById(quizId)).willReturn(Optional.of(quiz));
            given(quizMapper.toResponse(quiz)).willReturn(response);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            QuizDTOResponse result = quizService.findQuizById(quizId);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(quizId);
            assertThat(result.getTitle()).isEqualTo("Found Quiz");
            assertThat(result.getDescription()).isEqualTo("Found Desc");

            then(quizRepository).should().findById(quizId);
            then(quizMapper).should().toResponse(quiz);
            // Assert Проверка результата <--
        }

        @Test
        @DisplayName("должен выбросить ResourceNotFoundException, если квиз не найден")
        void shouldThrowException_whenQuizNotFound() {
            // Arrange Подготовка данных -->
            Long quizId = 999L;

            given(quizRepository.findById(quizId)).willReturn(Optional.empty());
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            // Assert Проверка результата -->
            assertThatThrownBy(() -> quizService.findQuizById(quizId))
                    .isInstanceOf(ResourceNotFoundException.class)  // Проверяем тип исключения
                    .hasMessage("Квиз не найден");
            // Act Вызов тестируемого метода <--

            then(quizMapper).shouldHaveNoInteractions();
            // Assert Проверка результата <--
        }

        @ParameterizedTest
        @ValueSource(longs = {1L, 2L, 100L, 999L, 12345L})
        @DisplayName("должен найти квиз для любого валидного ID")
        void shouldFindQuiz_forVariousValidIds(Long quizId) {
            // Arrange Подготовка данных -->
            var quiz = createQuiz(quizId, "Quiz " + quizId, "Desc");
            var response = createQuizResponse(quizId, "Quiz " + quizId, "Desc");

            given(quizRepository.findById(quizId)).willReturn(Optional.of(quiz));
            given(quizMapper.toResponse(quiz)).willReturn(response);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            var result = quizService.findQuizById(quizId);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result.getId()).isEqualTo(quizId);
            assertThat(result.getTitle()).isEqualTo("Quiz " + quizId);
            // Assert Проверка результата <--
        }


    }
    // Вложенный класс для тестирования метода findQuizById() <--

    // Вложенный класс для тестирования метода createQuiz() -->
    @Nested
    @DisplayName("createQuiz() — создание нового квиза")
    class CreateQuizTests {

        @Test
        @DisplayName("должен создать квиз без вопросов, если questions = null")
        void shouldCreateQuizWithoutQuestions_whenQuestionsIsNull() {
            // Arrange Подготовка данных -->
            var request = createQuizRequest("New Quiz", "Test description");

            var quizEntity = createQuiz(1L, "New Quiz", "Test description");
            var response = createQuizResponse(1L, "New Quiz", "Test description");

            given(quizMapper.toEntity(request)).willReturn(quizEntity);
            given(quizRepository.save(quizEntity)).willReturn(quizEntity);
            given(quizMapper.toResponse(quizEntity)).willReturn(response);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            QuizDTOResponse result = quizService.createQuiz(request);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result).isNotNull();
            assertThat(result.getTitle()).isEqualTo("New Quiz");

            then(quizRepository).should().save(quizEntity);
            then(answerRepository).shouldHaveNoInteractions();
            then(puzzleRepository).shouldHaveNoInteractions();
            // Assert Проверка результата <--
        }

        @Test
        @DisplayName("должен создать квиз с одним вопросом и ответом")
        void shouldCreateQuizWithSingleQuestion() {
            // Arrange Подготовка данных -->
            var puzzleRequest = PuzzleDTORequest.builder()
                    .question("Сколько будет 2 + 2?")
                    .questCategory(QuestCategory.NAME)
                    .build();

            var request = QuizDTORequest.builder()
                    .title("Math Quiz")
                    .description("Quiz about math")
                    .questions(List.of(puzzleRequest))
                    .build();

            var quizEntity = createQuiz(1L, "Math Quiz", "Quiz about math");
            var answerEntity = Answer.builder()
                    .id(1L)
                    .answer("")  // TODO: пока пустой ответ
                    .questCategory(QuestCategory.NAME)
                    .build();
            var puzzleEntity = Puzzle.builder()
                    .id(1L)
                    .question("Сколько будет 2 + 2?")
                    .answer(answerEntity)
                    .questCategory(QuestCategory.NAME)
                    .build();
            var response = createQuizResponse(1L, "Math Quiz", "Quiz about math");

            given(quizMapper.toEntity(request)).willReturn(quizEntity);
            given(quizMapper.toResponse(any(Quiz.class))).willReturn(response);

            given(answerRepository.save(any(Answer.class))).willReturn(answerEntity);
            given(puzzleRepository.save(any(Puzzle.class))).willReturn(puzzleEntity);
            given(quizRepository.save(any(Quiz.class))).willReturn(quizEntity);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            QuizDTOResponse result = quizService.createQuiz(request);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result).isNotNull();
            assertThat(result.getTitle()).isEqualTo("Math Quiz");

            then(answerRepository).should().save(any(Answer.class));
            then(puzzleRepository).should().save(any(Puzzle.class));
            then(quizRepository).should().save(quizEntity);

            assertThat(quizEntity.getQuestions()).isNotEmpty();
            // Assert Проверка результата <--
        }

        @Test
        @DisplayName("должен создать квиз с несколькими вопросами")
        void shouldCreateQuizWithMultipleQuestions() {
            // Arrange Подготовка данных -->
            var puzzleRequest1 = PuzzleDTORequest.builder()
                    .question("Q1?").questCategory((QuestCategory.NAME)).build();
            var puzzleRequest2 = PuzzleDTORequest.builder()
                    .question("Q2?").questCategory((QuestCategory.NAME)).build();

            var request = QuizDTORequest.builder()
                    .title("Multi Quiz")
                    .questions(List.of(puzzleRequest1, puzzleRequest2))
                    .build();

            var quizEntity = createQuiz(1L, "Multi Quiz", "Desc");
            var response = createQuizResponse(1L, "Multi Quiz", "Desc");

            given(quizMapper.toEntity(request)).willReturn(quizEntity);
            given(quizMapper.toResponse(any(Quiz.class))).willReturn(response);
            given(answerRepository.save(any(Answer.class)))
                    .willAnswer(invocation -> invocation.getArgument(0));
            given(puzzleRepository.save(any(Puzzle.class)))
                    .willAnswer(invocation -> invocation.getArgument(0));
            given(quizRepository.save(any(Quiz.class))).willReturn(quizEntity);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            var result = quizService.createQuiz(request);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result).isNotNull();

            then(answerRepository).should(times(2)).save(any(Answer.class));
            then(puzzleRepository).should(times(2)).save(any(Puzzle.class));
            then(quizRepository).should().save(quizEntity);
            // Assert Проверка результата <--
        }

    }
    // Вложенный класс для тестирования метода createQuiz() <--

    // Вложенный класс для тестирования метода updateQuiz() -->
    @Nested
    @DisplayName("updateQuiz() — обновление существующего квиза")
    class UpdateQuizTests {

        @Test
        @DisplayName("должен обновить title и description квиза")
        void shouldUpdateQuizFields_whenQuizExists() {
            // Arrange Подготовка данных -->
            Long quizId = 1L;

            var existingQuiz = createQuiz(quizId, "Old Title", "Old Desc");

            var request = createQuizRequest("New Title", "New Desc");

            var updatedQuiz = createQuiz(quizId, "New Title", "New Desc");
            var response = createQuizResponse(quizId, "New Title", "New Desc");

            given(quizRepository.findById(quizId)).willReturn(Optional.of(existingQuiz));
            given(quizRepository.save(existingQuiz)).willReturn(updatedQuiz);
            given(quizMapper.toResponse(updatedQuiz)).willReturn(response);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            QuizDTOResponse result = quizService.updateQuiz(quizId, request);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result.getTitle()).isEqualTo("New Title");
            assertThat(result.getDescription()).isEqualTo("New Desc");

            assertThat(existingQuiz.getTitle()).isEqualTo("New Title");
            assertThat(existingQuiz.getDescription()).isEqualTo("New Desc");

            then(quizRepository).should().save(existingQuiz);
            then(quizMapper).should().toResponse(updatedQuiz);
            // Assert Проверка результата <--

        }

        @Test
        @DisplayName("должен выбросить исключение, если квиз не найден для обновления")
        void shouldThrowException_whenQuizNotFoundForUpdate() {
            // Arrange Подготовка данных -->
            Long quizId = 999L;
            var request = createQuizRequest("New Title", "New Desc");

            given(quizRepository.findById(quizId)).willReturn(Optional.empty());
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            // Assert Проверка результата -->
            assertThatThrownBy(() -> quizService.updateQuiz(quizId, request))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessage("Квиз не найден");
            // Act Вызов тестируемого метода <--

            then(quizRepository).should(never()).save(any());
            then(quizMapper).shouldHaveNoInteractions();
            // Assert Проверка результата <--
        }

        @ParameterizedTest
        @CsvSource({
                "1, 'New Title 1', 'New Desc 1'",
                "2, 'Updated Quiz', 'Updated description'",
                "100, 'Short', ''"  // Пустое описание — допустимо
        })
        @DisplayName("должен обновить квиз с разными входными данными")
        void shouldUpdateQuiz_withVariousData(Long id, String newTitle, String newDesc) {
            // Arrange Подготовка данных -->
            var existingQuiz = createQuiz(id, "Old", "Old");
            var request = createQuizRequest(newTitle, newDesc);
            var updatedQuiz = createQuiz(id, newTitle, newDesc);
            var response = createQuizResponse(id, newTitle, newDesc);

            given(quizRepository.findById(id)).willReturn(Optional.of(existingQuiz));
            given(quizRepository.save(existingQuiz)).willReturn(updatedQuiz);
            given(quizMapper.toResponse(updatedQuiz)).willReturn(response);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            var result = quizService.updateQuiz(id, request);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result.getTitle()).isEqualTo(newTitle);
            assertThat(result.getDescription()).isEqualTo(newDesc);
            assertThat(existingQuiz.getTitle()).isEqualTo(newTitle);
            // Assert Проверка результата <--
        }

    }
    // Вложенный класс для тестирования метода updateQuiz() <--

    // Вложенный класс для тестирования метода deleteQuiz() -->
    @Nested
    @DisplayName("deleteQuiz() — удаление квиза")
    class DeleteQuizTests {

        @Test
        @DisplayName("должен вернуть true и удалить квиз, если он существует")
        void shouldReturnTrueAndDelete_whenQuizExists() {
            // Arrange Подготовка данных -->
            Long quizId = 1L;
            var quiz = createQuiz(quizId, "To Delete", "Desc");

            given(quizRepository.existsById(quizId)).willReturn(true);
            given(quizRepository.findById(quizId)).willReturn(Optional.of(quiz));

            willDoNothing().given(quizRepository).delete(quiz);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            Boolean result = quizService.deleteQuiz(quizId);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result).isTrue();

            then(quizRepository).should().delete(quiz);
            // Assert Проверка результата <--
        }

        @Test
        @DisplayName("должен вернуть false, если квиз не существует")
        void shouldReturnFalse_whenQuizDoesNotExist() {
            // Arrange Подготовка данных -->
            Long quizId = 999L;

            given(quizRepository.existsById(quizId)).willReturn(false);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            Boolean result = quizService.deleteQuiz(quizId);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result).isFalse();

            then(quizRepository).should(never()).findById(any());
            then(quizRepository).should(never()).delete(any());
            // Assert Проверка результата <--
        }

        @ParameterizedTest
        @ValueSource(longs = {1L, 2L, 100L})
        @DisplayName("должен удалить квиз для любого существующего ID")
        void shouldDeleteQuiz_forExistingIds(Long quizId) {
            // Arrange Подготовка данных -->
            var quiz = createQuiz(quizId, "Quiz", "Desc");

            given(quizRepository.existsById(quizId)).willReturn(true);
            given(quizRepository.findById(quizId)).willReturn(Optional.of(quiz));
            willDoNothing().given(quizRepository).delete(quiz);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            var result = quizService.deleteQuiz(quizId);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result).isTrue();
            then(quizRepository).should().delete(quiz);
            // Assert Проверка результата <--
        }

    }
    // Вложенный класс для тестирования метода deleteQuiz() <--

    @Nested
    @DisplayName("Граничные случаи и безопасность")
    class EdgeCasesTests {

        @Test
        @DisplayName("должен обработать null в request.getQuestions() без ошибок")
        void shouldHandleNullQuestionsList() {
            // Arrange Подготовка данных -->
            var request = QuizDTORequest.builder()
                    .title("Quiz")
                    .description("Desc")
                    // questions не установлен → null
                    .build();

            var quizEntity = createQuiz(1L, "Quiz", "Desc");
            var response = createQuizResponse(1L, "Quiz", "Desc");

            given(quizMapper.toEntity(request)).willReturn(quizEntity);
            given(quizRepository.save(quizEntity)).willReturn(quizEntity);
            given(quizMapper.toResponse(quizEntity)).willReturn(response);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            var result = quizService.createQuiz(request);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result).isNotNull();

            then(puzzleRepository).shouldHaveNoInteractions();
            then(answerRepository).shouldHaveNoInteractions();
            // Assert Проверка результата <--
        }

        @Test
        @DisplayName("должен обработать пустой список вопросов")
        void shouldHandleEmptyQuestionsList() {
            // Arrange Подготовка данных -->
            var request = QuizDTORequest.builder()
                    .title("Quiz")
                    .questions(Collections.emptyList())  // Пустой список, не null
                    .build();


            var quizEntity = createQuiz(1L, "Quiz", "Desc");
            var response = createQuizResponse(1L, "Quiz", "Desc");

            given(quizMapper.toEntity(request)).willReturn(quizEntity);
            given(quizRepository.save(quizEntity)).willReturn(quizEntity);
            given(quizMapper.toResponse(quizEntity)).willReturn(response);
            // Arrange Подготовка данных <--

            // Act Вызов тестируемого метода -->
            var result = quizService.createQuiz(request);
            // Act Вызов тестируемого метода <--

            // Assert Проверка результата -->
            assertThat(result).isNotNull();

            then(puzzleRepository).shouldHaveNoInteractions();
            then(answerRepository).shouldHaveNoInteractions();
            // Assert Проверка результата <--
        }

    }

}
