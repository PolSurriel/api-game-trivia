package com.example.trivia.repository;

import com.example.trivia.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    QuestionEntity findByApiId(String apiId);
}
