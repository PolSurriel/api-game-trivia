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

@RestController()
public class MatchControllerAPIController extends BaseRestController implements MatchControllerAPI{

    @Autowired
    private MatchService matchService;

    @Autowired
    private QuestionDTOMapper questionInfoResponseMapper;
    @Autowired
    private AnswerQuestionResultResponseMapper answerQuestionResultResponseMapper;
    @Autowired
    private GameDTOMapper gameDTOMapper;

    @Override
    public ResponseEntity<QuestionDTO> getQuestionInfo(Long gameId, Integer questionId) {
        Question question = matchService.getQuestionInfo(gameId, questionId);

        return new ResponseEntity<>(
                questionInfoResponseMapper.map(question),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<AnswerQuestionResultResponse> answerQuestion(Long gameId, Integer questionId, AwnserRequest answer) {
        AnswerQuestionResult result = matchService.submitAnswer(gameId, questionId, answer.getAnswerId());

        return new ResponseEntity<>(
                answerQuestionResultResponseMapper.map(result),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<HttpStatus> finishGame(Long gameId) {
        matchService.finishFame(gameId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GameDTO> getGameById(Long gameId) {
        Game game = matchService.getGameById(gameId);

        return new ResponseEntity<>(
            gameDTOMapper.map(game),
            HttpStatus.OK
        );
    }

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
