package com.example.web_qwiz_app.domain.model.entity;

import com.example.web_qwiz_app.domain.model.enums.QuestCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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
