package com.example.trivia.repository;

import com.example.trivia.entity.GameQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameQuestionRepository extends JpaRepository<GameQuestionEntity, Long> {
}
