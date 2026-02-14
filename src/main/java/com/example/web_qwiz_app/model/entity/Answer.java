package com.example.web_qwiz_app.model.entity;

import com.example.web_qwiz_app.model.enu.QuestCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String answer;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    private List<Puzzle> puzzles = new ArrayList<>();

    @Column(nullable = false)
    private QuestCategory questCategory;

}
