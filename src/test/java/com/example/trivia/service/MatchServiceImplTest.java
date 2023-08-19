package com.example.trivia.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.example.trivia.client.TriviaAPIClient;
import com.example.trivia.entity.AnswerEntity;
import com.example.trivia.entity.GameEntity;
import com.example.trivia.entity.GameQuestionEntity;
import com.example.trivia.entity.QuestionEntity;
import com.example.trivia.mappers.entity.QuestionEntityMapper;
import com.example.trivia.mappers.model.GameMapper;
import com.example.trivia.mappers.model.QuestionMapper;
import com.example.trivia.model.AnswerQuestionResult;
import com.example.trivia.model.Game;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameDifficulty;
import com.example.trivia.repository.AnswerRepository;
import com.example.trivia.repository.GameRepository;
import com.example.trivia.repository.QuestionRepository;
import com.example.trivia.service.impl.MatchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

public class MatchServiceImplTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private QuestionEntityMapper questionEntityMapper;

    @Mock
    private QuestionMapper questionMapper;

    @Mock
    private GameMapper gameEntityMapper;

    @Mock
    private TriviaAPIClient triviaAPIClient;

    @InjectMocks
    private MatchServiceImpl matchService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGameById() {
        GameEntity gameEntity = new GameEntity();
        when(gameRepository.findById(1L)).thenReturn(Optional.of(gameEntity));
        when(gameEntityMapper.map(gameEntity)).thenReturn(new Game());

        Game result = matchService.getGameById(1L);

        assertNotNull(result);
    }

    @Test
    public void testCreateGame() {
        // Mock the trivia API client to return a question
        when(triviaAPIClient.obtainQuestions(anyInt(), anyList(), any(GameDifficulty.class)))
                .thenReturn(Flux.just(new Question()));

        // Mock the question repository to simulate that the question does not exist in the DB
        when(questionRepository.findByApiId(anyString())).thenReturn(null);

        // Mock the questionEntityMapper to return a new QuestionEntity with an empty list of answers
        QuestionEntity mockQuestionEntity = new QuestionEntity();
        mockQuestionEntity.setAnswers(new ArrayList<>());
        when(questionEntityMapper.map(any(Question.class))).thenReturn(mockQuestionEntity);

        // Mock the gameEntityMapper to return a new game
        when(gameEntityMapper.map(any(GameEntity.class))).thenReturn(new Game());

        // Mock the question repository to return a new QuestionEntity
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setAnswers(new ArrayList<>());
        when(questionRepository.save(any(QuestionEntity.class))).thenReturn(questionEntity);

        Game result = matchService.createGame(10, GameDifficulty.EASY, new ArrayList<>());

        assertNotNull(result);
    }


    @Test
    public void testSubmitAnswer() {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setFinished(false);
        GameQuestionEntity gameQuestion = new GameQuestionEntity();
        QuestionEntity questionEntity = new QuestionEntity();
        List<AnswerEntity> answers = new ArrayList<>();
        answers.add(new AnswerEntity());
        answers.get(0).setCorrect(true);
        questionEntity.setAnswers(answers);
        gameQuestion.setQuestion(questionEntity);
        AnswerEntity correctAnswer = new AnswerEntity();
        correctAnswer.setCorrect(true);


        gameEntity.setQuestions(Arrays.asList(gameQuestion));

        when(gameRepository.findById(1L)).thenReturn(Optional.of(gameEntity));
        when(answerRepository.findById(1L)).thenReturn(Optional.of(new AnswerEntity()));

        AnswerQuestionResult result = matchService.submitAnswer(1L, 0, 1L);

        assertNotNull(result);
    }

    @Test
    public void testGetQuestionInfo() {
        GameEntity gameEntity = new GameEntity();
        GameQuestionEntity gameQuestion = new GameQuestionEntity();
        gameQuestion.setQuestion(new QuestionEntity());
        gameEntity.setQuestions(Arrays.asList(gameQuestion));
        gameEntity.setFinished(false);

        when(gameRepository.findById(1L)).thenReturn(Optional.of(gameEntity));
        when(questionMapper.map(any(QuestionEntity.class))).thenReturn(new Question());

        Question result = matchService.getQuestionInfo(1L, 0);

        assertNotNull(result);
    }

    @Test
    public void testFinishGame() {
        GameEntity gameEntity = new GameEntity();

        when(gameRepository.findById(1L)).thenReturn(Optional.of(gameEntity));

        assertDoesNotThrow(() -> matchService.finishFame(1L));
    }

    @Test
    public void shouldThrowExceptionWhenGameNotFound() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> matchService.getGameById(1L));
    }

    @Test
    public void shouldThrowExceptionWhenAnswerNotFound() {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setFinished(false);
        GameQuestionEntity gameQuestion = new GameQuestionEntity();
        gameEntity.setQuestions(Arrays.asList(gameQuestion));

        when(gameRepository.findById(1L)).thenReturn(Optional.of(gameEntity));
        when(answerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> matchService.submitAnswer(1L, 0, 1L));
    }

}
