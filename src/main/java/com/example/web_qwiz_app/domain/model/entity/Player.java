package com.example.web_qwiz_app.domain.model.entity;

import com.example.web_qwiz_app.domain.model.enums.Faculty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="players")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String firstName;

    @Enumerated(EnumType.STRING)
    private Faculty faculty;

}
