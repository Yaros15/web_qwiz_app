package com.example.web_qwiz_app.repository;

import com.example.web_qwiz_app.model.entity.Answer;
import com.example.web_qwiz_app.model.enu.QuestCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestCategory(QuestCategory questCategory);

}
