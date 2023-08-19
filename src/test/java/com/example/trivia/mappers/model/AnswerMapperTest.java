package com.example.trivia.mappers.model;

import com.example.trivia.entity.AnswerEntity;
import com.example.trivia.model.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerMapperTest {

    private AnswerMapper answerMapper;

    @BeforeEach
    public void setUp() {
        answerMapper = new AnswerMapper();
    }

    @Test
    public void shouldMapAnswerEntityToAnswer() {
        // Given
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setId(1L);
        answerEntity.setText("Sample AnswerEntity Text");
        answerEntity.setCorrect(true);

        // When
        Answer answer = answerMapper.map(answerEntity);

        // Then
        assertEquals(answerEntity.getId(), answer.getId());
        assertEquals(answerEntity.getText(), answer.getText());
        assertEquals(answerEntity.getCorrect(), answer.getCorrect());
    }
}
