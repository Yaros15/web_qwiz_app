package com.example.web_qwiz_app.domain.repository;

import com.example.web_qwiz_app.domain.model.entity.Puzzle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuzzleRepository extends JpaRepository<Puzzle, Long> {

    List<Puzzle> findByAnswerId(Long answerId);

}
