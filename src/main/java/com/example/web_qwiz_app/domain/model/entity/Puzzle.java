package com.example.web_qwiz_app.domain.model.entity;

import com.example.web_qwiz_app.domain.model.enums.QuestCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="puzzle")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Puzzle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private QuestCategory questCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

}
