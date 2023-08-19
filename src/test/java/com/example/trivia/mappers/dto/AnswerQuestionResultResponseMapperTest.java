package com.example.trivia.mappers.dto;

import com.example.trivia.api.v1.dto.response.AnswerQuestionResultResponse;
import com.example.trivia.mappers.dto.AnswerQuestionResultResponseMapper;
import com.example.trivia.model.AnswerQuestionResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class AnswerQuestionResultResponseMapperTest {

    @InjectMocks
    private AnswerQuestionResultResponseMapper answerQuestionResultResponseMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldMapAnswerQuestionResultToAnswerQuestionResultResponse() {
        // Given
        AnswerQuestionResult answerQuestionResult = new AnswerQuestionResult();
        answerQuestionResult.setIsCorrect(true);
        answerQuestionResult.setCorrectAnswerId(1L);
        answerQuestionResult.setCorrectAnswerIndex(2);

        // When
        AnswerQuestionResultResponse answerQuestionResultResponse = answerQuestionResultResponseMapper.map(answerQuestionResult);

        // Then
        assertEquals(answerQuestionResult.getCorrectAnswerId(), answerQuestionResultResponse.getCorrectAnswerId());
    }
}
