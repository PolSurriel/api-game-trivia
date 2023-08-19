package com.example.trivia.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.trivia.api.v1.controller.MatchControllerAPIController;
import com.example.trivia.api.v1.dto.GameDTO;
import com.example.trivia.api.v1.dto.QuestionDTO;
import com.example.trivia.api.v1.dto.request.AwnserRequest;
import com.example.trivia.api.v1.dto.request.GameConfigRequest;
import com.example.trivia.api.v1.dto.response.AnswerQuestionResultResponse;
import com.example.trivia.mappers.dto.AnswerQuestionResultResponseMapper;
import com.example.trivia.mappers.dto.GameDTOMapper;
import com.example.trivia.mappers.dto.QuestionDTOMapper;
import com.example.trivia.model.AnswerQuestionResult;
import com.example.trivia.model.Game;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import com.example.trivia.service.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

class MatchControllerAPIControllerTest {

    @InjectMocks
    private MatchControllerAPIController controller;

    @Mock
    private MatchService matchService;
    @Mock
    private QuestionDTOMapper questionInfoResponseMapper;
    @Mock
    private AnswerQuestionResultResponseMapper answerQuestionResultResponseMapper;
    @Mock
    private GameDTOMapper gameDTOMapper;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getQuestionInfoReturnsOk() {
        Long gameId = 1L;
        Integer questionId = 1;
        Question mockQuestion = new Question();
        when(matchService.getQuestionInfo(gameId, questionId)).thenReturn(mockQuestion);
        when(questionInfoResponseMapper.map(mockQuestion)).thenReturn(new QuestionDTO());

        ResponseEntity<QuestionDTO> response = controller.getQuestionInfo(gameId, questionId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void answerQuestionReturnsCreated() {
        Long gameId = 1L;
        Integer questionId = 1;
        AwnserRequest request = new AwnserRequest();
        AnswerQuestionResult mockResult = new AnswerQuestionResult();
        when(matchService.submitAnswer(gameId, questionId, request.getAnswerId())).thenReturn(mockResult);
        when(answerQuestionResultResponseMapper.map(mockResult)).thenReturn(new AnswerQuestionResultResponse());

        ResponseEntity<AnswerQuestionResultResponse> response = controller.answerQuestion(gameId, questionId, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void finishGameReturnsCreated() {
        Long gameId = 1L;

        ResponseEntity<HttpStatus> response = controller.finishGame(gameId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void getGameByIdReturnsOk() {
        Long gameId = 1L;
        Game mockGame = new Game();
        when(matchService.getGameById(gameId)).thenReturn(mockGame);
        when(gameDTOMapper.map(mockGame)).thenReturn(new GameDTO());

        ResponseEntity<GameDTO> response = controller.getGameById(gameId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    // Additional tests for error scenarios, edge cases, etc. can be added.

    @Test
    void createNewGameReturnsCreated() {
        GameConfigRequest gameConfigRequest = new GameConfigRequest();
        gameConfigRequest.setDifficulty(GameDifficulty.EASY.getDifficultyLevel());
        gameConfigRequest.setCategories(List.of(GameCategory.ARTS_AND_LITERATURE.getCategoryName()));
        Game mockGame = new Game();
        when(matchService.createGame(
                gameConfigRequest.getNumberOfQuestions(),
                GameDifficulty.fromString(gameConfigRequest.getDifficulty()),
                gameConfigRequest.getCategories().stream().map(GameCategory::fromString).toList()
        )).thenReturn(mockGame);
        when(gameDTOMapper.map(mockGame)).thenReturn(new GameDTO());

        ResponseEntity<GameDTO> response = controller.createNewGame(gameConfigRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}