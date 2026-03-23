package com.example.web_qwiz_app.config;

import com.example.web_qwiz_app.domain.model.entity.Answer;
import com.example.web_qwiz_app.domain.model.entity.Puzzle;
import com.example.web_qwiz_app.domain.model.entity.Quiz;
import com.example.web_qwiz_app.domain.model.entity.User;
import com.example.web_qwiz_app.domain.model.enums.QuestCategory;
import com.example.web_qwiz_app.domain.model.enums.Role;
import com.example.web_qwiz_app.domain.repository.AnswerRepository;
import com.example.web_qwiz_app.domain.repository.PuzzleRepository;
import com.example.web_qwiz_app.domain.repository.QuizRepository;
import com.example.web_qwiz_app.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev") // Выполнять только в профиле dev
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AnswerRepository answerRepository;
    private final PuzzleRepository puzzleRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0)
            initUserTable();
        if (answerRepository.count() == 0)
            initAnswerTable();
        if(quizRepository.count() == 0)
            initQuizTable();
        if (puzzleRepository.count() == 0)
            initPuzzleTable();
    }

    public void initUserTable(){
        userRepository.save(User.builder().username("admin").email("admin@quiz.com").password(passwordEncoder.encode("admin123")).role(Role.ROLE_ADMIN).build());
        userRepository.save(User.builder().username("user").email("user@quiz.com").password(passwordEncoder.encode("user123")).role(Role.ROLE_USER).build());
    }

    public void initAnswerTable(){
        answerRepository.save(Answer.builder().answer("Гарри").questCategory(QuestCategory.NAME).build());
        answerRepository.save(Answer.builder().answer("Гермиона").questCategory(QuestCategory.NAME).build());
        answerRepository.save(Answer.builder().answer("Рон").questCategory(QuestCategory.NAME).build());
        answerRepository.save(Answer.builder().answer("Драко").questCategory(QuestCategory.NAME).build());
        answerRepository.save(Answer.builder().answer("Невил").questCategory(QuestCategory.NAME).build());
        answerRepository.save(Answer.builder().answer("Минерва").questCategory(QuestCategory.NAME).build());
        answerRepository.save(Answer.builder().answer("Альбус").questCategory(QuestCategory.NAME).build());
        answerRepository.save(Answer.builder().answer("Северус").questCategory(QuestCategory.NAME).build());
        answerRepository.save(Answer.builder().answer("Далорас").questCategory(QuestCategory.NAME).build());
        answerRepository.save(Answer.builder().answer("Том").questCategory(QuestCategory.NAME).build());
        answerRepository.save(Answer.builder().answer("Палумна").questCategory(QuestCategory.NAME).build());
        answerRepository.save(Answer.builder().answer("Арагок").questCategory(QuestCategory.NAME).build());
    }

    public void initQuizTable(){
        quizRepository.save(Quiz.builder().title("First").description("My first quiz").author(userRepository.findById(1L).orElse(null)).build());
        quizRepository.save(Quiz.builder().title("Second").description("My second quiz").author(userRepository.findById(2L).orElse(null)).build());
    }

    public void initPuzzleTable(){
        puzzleRepository.save(Puzzle.builder().question("Имя Поттера?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(1L).orElse(null)).quiz(quizRepository.findById(1L).orElse(null)).build());
        puzzleRepository.save(Puzzle.builder().question("Имя Грейнджер?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(2L).orElse(null)).quiz(quizRepository.findById(1L).orElse(null)).build());
        puzzleRepository.save(Puzzle.builder().question("Имя Уйзли?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(3L).orElse(null)).quiz(quizRepository.findById(1L).orElse(null)).build());
        puzzleRepository.save(Puzzle.builder().question("Имя Малфоя?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(4L).orElse(null)).quiz(quizRepository.findById(1L).orElse(null)).build());
        puzzleRepository.save(Puzzle.builder().question("Имя Долгопупса?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(5L).orElse(null)).quiz(quizRepository.findById(1L).orElse(null)).build());
        puzzleRepository.save(Puzzle.builder().question("Имя Макгонагал?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(6L).orElse(null)).quiz(quizRepository.findById(1L).orElse(null)).build());
        puzzleRepository.save(Puzzle.builder().question("Имя Дамболдора?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(7L).orElse(null)).quiz(quizRepository.findById(2L).orElse(null)).build());
        puzzleRepository.save(Puzzle.builder().question("Имя Снейпа?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(8L).orElse(null)).quiz(quizRepository.findById(2L).orElse(null)).build());
        puzzleRepository.save(Puzzle.builder().question("Имя Амбридж?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(9L).orElse(null)).quiz(quizRepository.findById(2L).orElse(null)).build());
        puzzleRepository.save(Puzzle.builder().question("Имя Редла?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(10L).orElse(null)).quiz(quizRepository.findById(2L).orElse(null)).build());
        puzzleRepository.save(Puzzle.builder().question("Имя Лавгуд?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(11L).orElse(null)).quiz(quizRepository.findById(2L).orElse(null)).build());
        puzzleRepository.save(Puzzle.builder().question("Имя Паука?").questCategory(QuestCategory.NAME).answer(answerRepository.findById(12L).orElse(null)).quiz(quizRepository.findById(2L).orElse(null)).build());
    }

}
