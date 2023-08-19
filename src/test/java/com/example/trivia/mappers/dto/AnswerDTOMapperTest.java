package com.example.trivia.mappers.dto;

import com.example.trivia.api.v1.dto.AnswerDTO;
import com.example.trivia.mappers.dto.AnswerDTOMapper;
import com.example.trivia.model.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class AnswerDTOMapperTest {

    @InjectMocks
    private AnswerDTOMapper answerDTOMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldMapAnswerToAnswerDTO() {
        // Given
        Answer answer = new Answer();
        answer.setId(1L);
        answer.setText("Test Answer");
        answer.setCorrect(true);

        // When
        AnswerDTO answerDTO = answerDTOMapper.map(answer);

        // Then
        assertEquals(answer.getId(), answerDTO.getId());
        assertEquals(answer.getText(), answerDTO.getText());
    }
}
