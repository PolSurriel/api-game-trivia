package com.example.trivia.mappers.model;

import com.example.trivia.api.v1.dto.QuestionDTO;
import com.example.trivia.client.dto.TriviaQuestionDTO;
import com.example.trivia.entity.AnswerEntity;
import com.example.trivia.entity.QuestionEntity;
import com.example.trivia.model.Answer;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class QuestionMapperTest {

    @InjectMocks
    private QuestionMapper questionMapper;

    @Mock
    private AnswerMapper answerMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldMapQuestionEntityToQuestion() {
        // Given
        QuestionEntity entity = new QuestionEntity();
        entity.setId(1L);
        entity.setApiId("123");
        entity.setCategory(GameCategory.MUSIC);
        entity.setText("Sample Question");

        AnswerEntity answerEntity1 = new AnswerEntity();
        AnswerEntity answerEntity2 = new AnswerEntity();
        entity.setAnswers(Arrays.asList(answerEntity1, answerEntity2));

        Answer answer1 = new Answer();
        Answer answer2 = new Answer();

        when(answerMapper.map(answerEntity1)).thenReturn(answer1);
        when(answerMapper.map(answerEntity2)).thenReturn(answer2);

        // When
        Question question = questionMapper.map(entity);

        // Then
        assertEquals(entity.getId(), question.getId());
        assertEquals(entity.getApiId(), question.getApiId());
        assertEquals(entity.getCategory(), question.getCategory());
        assertEquals(entity.getText(), question.getText());
        assertEquals(2, question.getAnswers().size());
        assertEquals(answer1, question.getAnswers().get(0));
        assertEquals(answer2, question.getAnswers().get(1));
    }

    @Test
    public void shouldMapTriviaQuestionDTOToQuestion() {
        // Given
        TriviaQuestionDTO dto = new TriviaQuestionDTO();
        dto.setId("123");
        dto.setCategory("MUSIC");
        dto.setQuestion(new TriviaQuestionDTO.QuestionText());
        dto.getQuestion().setText("Sample Question");
        dto.setCorrectAnswer("Correct");
        dto.setIncorrectAnswers(Arrays.asList("Wrong1", "Wrong2"));

        // When
        Question question = questionMapper.map(dto);

        // Then
        assertEquals(dto.getId(), question.getApiId());
        assertEquals(GameCategory.MUSIC, question.getCategory());
        assertEquals(dto.getQuestion().getText(), question.getText());
        assertEquals(3, question.getAnswers().size());
        assertEquals("Correct", question.getAnswers().get(2).getText());
        assertEquals(Boolean.TRUE, question.getAnswers().get(2).getCorrect());
    }
}
