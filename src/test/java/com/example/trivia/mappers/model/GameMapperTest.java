package com.example.trivia.mappers.model;

import com.example.trivia.entity.GameEntity;
import com.example.trivia.entity.GameQuestionEntity;
import com.example.trivia.entity.QuestionEntity;
import com.example.trivia.model.Game;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GameMapperTest {

    @InjectMocks
    private GameMapper gameMapper;

    @Mock
    private QuestionMapper questionMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldMapGameEntityToGame() {
        // Given
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(1L);
        gameEntity.setDifficulty(GameDifficulty.EASY);
        gameEntity.setFinished(true);
        gameEntity.setCategories(Arrays.asList(GameCategory.MUSIC, GameCategory.FILM_AND_TV));

        GameQuestionEntity gameQuestion1 = new GameQuestionEntity();
        QuestionEntity questionEntity1 = new QuestionEntity();
        gameQuestion1.setQuestion(questionEntity1);

        GameQuestionEntity gameQuestion2 = new GameQuestionEntity();
        QuestionEntity questionEntity2 = new QuestionEntity();
        gameQuestion2.setQuestion(questionEntity2);

        gameEntity.setQuestions(Arrays.asList(gameQuestion1, gameQuestion2));

        Question question1 = new Question();
        Question question2 = new Question();

        when(questionMapper.map(questionEntity1)).thenReturn(question1);
        when(questionMapper.map(questionEntity2)).thenReturn(question2);

        // When
        Game game = gameMapper.map(gameEntity);

        // Then
        assertEquals(gameEntity.getId(), game.getId());
        assertEquals(gameEntity.getDifficulty(), game.getDifficulty());
        assertEquals(gameEntity.getFinished(), game.getFinished());
        assertEquals(gameEntity.getCategories(), game.getCategories());
        assertEquals(2, game.getQuestions().size());
        assertEquals(question1, game.getQuestions().get(0));
        assertEquals(question2, game.getQuestions().get(1));
    }
}
