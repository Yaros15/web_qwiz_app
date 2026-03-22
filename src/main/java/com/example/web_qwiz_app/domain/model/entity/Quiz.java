package com.example.web_qwiz_app.domain.model.entity;

import jakarta.persistence.Entity;

import com.example.web_qwiz_app.domain.model.enums.QuestCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="quiz")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Puzzle> questions = new ArrayList<>();

    public void addPuzzle(Puzzle puzzle){
        questions.add(puzzle);
        puzzle.setQuiz(this);
    }

    public void removePuzzle(Puzzle puzzle){
        questions.remove(puzzle);
        puzzle.setQuiz(this);
    }

}
