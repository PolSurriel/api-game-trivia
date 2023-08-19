package com.example.trivia.mappers.entity;

import com.example.trivia.entity.AnswerEntity;
import com.example.trivia.model.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerEntityMapperTest {

    private AnswerEntityMapper answerEntityMapper;

    @BeforeEach
    public void setUp() {
        answerEntityMapper = new AnswerEntityMapper();
    }

    @Test
    public void shouldMapAnswerToAnswerEntity() {
        // Given
        Answer answer = new Answer();
        answer.setText("Sample Answer");
        answer.setCorrect(true);

        // When
        AnswerEntity answerEntity = answerEntityMapper.map(answer);

        // Then
        assertEquals(answer.getText(), answerEntity.getText());
        assertEquals(answer.getCorrect(), answerEntity.getCorrect());
    }
}
