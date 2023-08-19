package com.example.trivia.api.v1.controller;

import com.example.trivia.api.v1.dto.request.AwnserRequest;
import com.example.trivia.api.v1.dto.request.GameConfigRequest;
import com.example.trivia.api.v1.dto.response.AnswerQuestionResultResponse;
import com.example.trivia.api.v1.dto.GameDTO;
import com.example.trivia.api.v1.dto.QuestionDTO;
import com.example.trivia.mappers.dto.AnswerQuestionResultResponseMapper;
import com.example.trivia.mappers.dto.GameDTOMapper;
import com.example.trivia.mappers.dto.QuestionDTOMapper;
import com.example.trivia.model.AnswerQuestionResult;
import com.example.trivia.model.Game;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import com.example.trivia.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * The API controller implementation for the MatchController.
 * */
@RestController()
public class MatchControllerAPIController extends BaseRestController implements MatchControllerAPI{

    /**
     * The match service.
     * */
    @Autowired
    private MatchService matchService;
    /**
     * The question info response mapper.
     * */
    @Autowired
    private QuestionDTOMapper questionInfoResponseMapper;
    /**
     * The answer question result response mapper.
     * */
    @Autowired
    private AnswerQuestionResultResponseMapper answerQuestionResultResponseMapper;
    /**
     * The game dto mapper.
     * */
    @Autowired
    private GameDTOMapper gameDTOMapper;

    /**
     * Returns the information of the question.
     *
     *  @param gameId the id of the game.
     *  @param questionId the id of the question.
     *  @return the information of the question.
     * */
    @Override
    public ResponseEntity<QuestionDTO> getQuestionInfo(Long gameId, Integer questionId) {
        Question question = matchService.getQuestionInfo(gameId, questionId);

        return new ResponseEntity<>(
                questionInfoResponseMapper.map(question),
                HttpStatus.OK
        );
    }


    /**
     * Stores the user’s answer and returns the ID of
     * the wright answer. Chosen answer ID will be sent as a request body.
     * Important: A question cannot be answered twice: if a user sends an answer for an
     * already answered question, an error is thrown
     *
     *  @param gameId the id of the game.
     *  @param questionId the id of the question.
     *  @return the information of the question.
     * */
    @Override
    public ResponseEntity<AnswerQuestionResultResponse> answerQuestion(Long gameId, Integer questionId, AwnserRequest answer) {
        AnswerQuestionResult result = matchService.submitAnswer(gameId, questionId, answer.getAnswerId());

        return new ResponseEntity<>(
                answerQuestionResultResponseMapper.map(result),
                HttpStatus.CREATED
        );
    }

    /**
     * Finishes the game marking it as finished.
     *
     *  @param gameId the id of the game.
     *  @return the http status of the request.
     * */
    @Override
    public ResponseEntity<HttpStatus> finishGame(Long gameId) {
        matchService.finishFame(gameId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Returns the information of the game searched by id.
     *
     *  @param gameId the id of the game.
     *  @return the information of the game.
     * */
    @Override
    public ResponseEntity<GameDTO> getGameById(Long gameId) {
        Game game = matchService.getGameById(gameId);

        return new ResponseEntity<>(
            gameDTOMapper.map(game),
            HttpStatus.OK
        );
    }

    /**
     * Creates a new game with the given configuration.
     *
     *  @param gameConfig the configuration of the game.
     *  @return the information of the game.
     * */
    @Override
    public ResponseEntity<GameDTO> createNewGame(GameConfigRequest gameConfig) {
        Game game = matchService.createGame(
                gameConfig.getNumberOfQuestions(),
                GameDifficulty.fromString(gameConfig.getDifficulty()),
                gameConfig.getCategories().stream().map(GameCategory::fromString).toList()
        );
        return new ResponseEntity<>(gameDTOMapper.map(game), HttpStatus.CREATED);
    }
}
