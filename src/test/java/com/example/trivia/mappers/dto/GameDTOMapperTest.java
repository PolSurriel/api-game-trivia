package com.example.trivia.mappers.dto;

import com.example.trivia.api.v1.dto.GameDTO;
import com.example.trivia.api.v1.dto.QuestionDTO;
import com.example.trivia.model.Game;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GameDTOMapperTest {

    @InjectMocks
    private GameDTOMapper gameDTOMapper;

    @Mock
    private QuestionDTOMapper questionDTOMapper;

    @Mock
    private AnswerDTOMapper answerDTOMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldMapGameToGameDTO() {
        // Given
        Game game = new Game();
        game.setId(1L);
        game.setDifficulty(GameDifficulty.EASY);
        game.setFinished(true);
        game.setCategories(Arrays.asList(GameCategory.MUSIC, GameCategory.SCIENCE));

        Question question1 = new Question(); // Add necessary attributes
        Question question2 = new Question(); // Add necessary attributes
        game.setQuestions(Arrays.asList(question1, question2));

        QuestionDTO questionDTO1 = new QuestionDTO(); // Add necessary attributes
        QuestionDTO questionDTO2 = new QuestionDTO(); // Add necessary attributes

        when(questionDTOMapper.map(question1)).thenReturn(questionDTO1);
        when(questionDTOMapper.map(question2)).thenReturn(questionDTO2);

        // When
        GameDTO gameDTO = gameDTOMapper.map(game);

        // Then
        assertEquals(game.getId(), gameDTO.getId());
        assertEquals(game.getFinished(), gameDTO.getFinished());
        assertEquals(game.getDifficulty().getDifficultyLevel(), gameDTO.getDifficulty());
        assertEquals(game.getCategories().size(), gameDTO.getCategories().size());
        assertEquals(game.getQuestions().size(), gameDTO.getQuestions().size());
    }
}
