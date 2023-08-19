package com.example.trivia.api.v1.controller;

import com.example.trivia.api.v1.dto.request.AwnserRequest;
import com.example.trivia.api.v1.dto.request.GameConfigRequest;
import com.example.trivia.api.v1.dto.response.AwnserQuestionResultResponse;
import com.example.trivia.api.v1.dto.response.GameResponse;
import com.example.trivia.api.v1.dto.response.QuestionInfoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.trivia.api.v1.controller.APIDoc.API_VERSION;

@RestController()
public class MatchControllerAPIController extends BaseRestController implements MatchControllerAPI{

    @Override
    public ResponseEntity<QuestionInfoResponse> getQuestionInfo(Integer gameId, Integer questionId) {


        return null;
    }

    @Override
    public ResponseEntity<AwnserQuestionResultResponse> answerQuestion(Integer gameId, Integer questionId, AwnserRequest answer) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> finishGame(Integer gameId) {
        return null;
    }

    @Override
    public ResponseEntity<GameResponse> getGameById(Integer gameId) {
        return null;
    }

    @Override
    public ResponseEntity<GameResponse> createNewGame(GameConfigRequest gameConfig) {
        return null;
    }
}
