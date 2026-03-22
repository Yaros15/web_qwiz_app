package com.example.web_qwiz_app.domain.model.entity;

import com.example.web_qwiz_app.domain.model.enums.Faculty;
import com.example.web_qwiz_app.domain.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Faculty faculty;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Quiz> quizzes = new ArrayList<>();

    public void addQuiz(Quiz quiz){
        quizzes.add(quiz);
        quiz.setAuthor(this);
    }

    public void removeQuiz(Quiz quiz){
        quizzes.remove(quiz);
        quiz.setAuthor(this);
    }

}
