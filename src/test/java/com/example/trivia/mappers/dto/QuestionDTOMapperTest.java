package com.example.trivia.mappers.dto;

import com.example.trivia.api.v1.dto.AnswerDTO;
import com.example.trivia.api.v1.dto.QuestionDTO;
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

public class QuestionDTOMapperTest {

    @InjectMocks
    private QuestionDTOMapper questionDTOMapper;

    @Mock
    private AnswerDTOMapper answerDTOMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldMapQuestionToQuestionDTO() {
        // Given
        Question question = new Question();
        question.setId(1L);
        question.setText("Sample Question?");
        question.setApiId("api12345");
        question.setCategory(GameCategory.MUSIC);

        Answer answer1 = new Answer();
        answer1.setId(1L);
        answer1.setText("Sample Answer 1");

        Answer answer2 = new Answer();
        answer2.setId(2L);
        answer2.setText("Sample Answer 2");

        question.setAnswers(Arrays.asList(answer1, answer2));

        AnswerDTO answerDTO1 = new AnswerDTO();
        answerDTO1.setId(1L);
        answerDTO1.setText("Sample Answer 1");

        AnswerDTO answerDTO2 = new AnswerDTO();
        answerDTO2.setId(2L);
        answerDTO2.setText("Sample Answer 2");

        when(answerDTOMapper.map(answer1)).thenReturn(answerDTO1);
        when(answerDTOMapper.map(answer2)).thenReturn(answerDTO2);

        // When
        QuestionDTO questionDTO = questionDTOMapper.map(question);

        // Then
        assertEquals(question.getId(), questionDTO.getId());
        assertEquals(question.getText(), questionDTO.getText());
//        assertEquals(question.getApiId(), questionDTO.getApiId());
        assertEquals(question.getCategory().getCategoryName(), questionDTO.getCategory());
        assertEquals(question.getAnswers().size(), questionDTO.getAnswers().size());
    }
}
