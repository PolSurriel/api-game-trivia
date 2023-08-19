package com.example.trivia.mappers.entity;

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

public class QuestionEntityMapperTest {

    @InjectMocks
    private QuestionEntityMapper questionEntityMapper;

    @Mock
    private AnswerEntityMapper answerEntityMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldMapQuestionToQuestionEntity() {
        // Given
        Question question = new Question();
        question.setApiId("api12345");
        question.setCategory(GameCategory.MUSIC);
        question.setText("Sample Question?");

        Answer answer1 = new Answer();
        answer1.setText("Sample Answer 1");
        answer1.setCorrect(true);

        Answer answer2 = new Answer();
        answer2.setText("Sample Answer 2");
        answer2.setCorrect(false);

        question.setAnswers(Arrays.asList(answer1, answer2));

        AnswerEntity answerEntity1 = new AnswerEntity();
        answerEntity1.setText("Sample Answer 1");
        answerEntity1.setCorrect(true);

        AnswerEntity answerEntity2 = new AnswerEntity();
        answerEntity2.setText("Sample Answer 2");
        answerEntity2.setCorrect(false);

        when(answerEntityMapper.map(answer1)).thenReturn(answerEntity1);
        when(answerEntityMapper.map(answer2)).thenReturn(answerEntity2);

        // When
        QuestionEntity questionEntity = questionEntityMapper.map(question);

        // Then
        assertEquals(question.getApiId(), questionEntity.getApiId());
        assertEquals(question.getCategory(), questionEntity.getCategory());
        assertEquals(question.getText(), questionEntity.getText());
        assertEquals(question.getAnswers().size(), questionEntity.getAnswers().size());
    }
}
