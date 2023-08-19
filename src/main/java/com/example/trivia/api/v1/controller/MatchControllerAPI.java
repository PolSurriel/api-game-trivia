package com.example.trivia.api.v1.controller;


import com.example.trivia.api.v1.dto.request.AwnserRequest;
import com.example.trivia.api.v1.dto.request.GameConfigRequest;
import com.example.trivia.api.v1.dto.response.AnswerQuestionResultResponse;
import com.example.trivia.api.v1.dto.GameDTO;
import com.example.trivia.api.v1.dto.QuestionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.trivia.api.v1.controller.MatchControllerAPI.MATCH_TAG;

/**
 * The API controller for the MatchController.
 * */
@Validated
@Api(tags = {MATCH_TAG})
public interface MatchControllerAPI {

    String MATCH_TAG = "Match";

    @ApiOperation(
            value = "Get question information given a game id and a question id.",
            nickname = "getQuestionInfo",
            notes = "Returns the information of the question. Attention:\n" +
                    "we will use the order field instead of the id to retrieve question information. Correct\n" +
                    "boolean shouldn't be sent because the POST endpoint will return the answer\n" +
                    "results.",
            response = QuestionDTO.class,
            tags={ MATCH_TAG }
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = APIDoc.RESPONSE_MESSAGE_OK +" question found", response = QuestionDTO.class),
            @ApiResponse(code = 400, message = APIDoc.RESPONSE_MESSAGE_BAD_REQUEST +"Invalid IDs supplied"),
            @ApiResponse(code = 404, message = APIDoc.RESPONSE_MESSAGE_NOT_FOUND+" Question not found") }
    )
    @GetMapping(
            value = "/game/{gameId}/question/{questionId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    ResponseEntity<QuestionDTO> getQuestionInfo(
            @ApiParam(value = "ID of the game",required=true)
            @PathVariable("gameId")
            Long gameId,

            @ApiParam(value = "ID of the question to return",required=true)
            @PathVariable("questionId")
            Integer questionId
    );

    @ApiOperation(
            value = "Save user’s answer and get wright answer's ID.",
            nickname = "answerQuestion",
            notes = "Stores the user’s answer and returns the ID of\n" +
                    "the wright answer. Chosen answer ID will be sent as a request body.\n" +
                    "Important: A question cannot be answered twice: if a user sends an answer for an\n" +
                    "already answered question, an error is thrown",
            response = AnswerQuestionResultResponse.class,
            tags={ MATCH_TAG }
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = APIDoc.RESPONSE_MESSAGE_CREATED +" question answered", response = AnswerQuestionResultResponse.class),
            @ApiResponse(code = 400, message = APIDoc.RESPONSE_MESSAGE_BAD_REQUEST +" Invalid IDs supplied"),
            @ApiResponse(code = 404, message = APIDoc.RESPONSE_MESSAGE_NOT_FOUND+" Game/Question not found") }
    )
    @PostMapping(
            value = "/game/{gameId}/question/{questionId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    ResponseEntity<AnswerQuestionResultResponse> answerQuestion(
            @ApiParam(value = "ID of the game",required=true)
            @PathVariable("gameId")
            Long gameId,

            @ApiParam(value = "ID of the question to return",required=true)
            @PathVariable("questionId")
            Integer questionId,

            @ApiParam(value = "Chosen answer", required = true)
            @RequestBody
            AwnserRequest answer
    );

    @ApiOperation(
            value = "Ends the game.",
            nickname = "finishGame",
            notes = "Ends the game. The game can end at any time, even if there\n" +
                    "are still answers to be given. When a game is ended, it cannot be updated never\n" +
                    "more.",
            response = HttpStatus.class,
            tags={ MATCH_TAG }
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = APIDoc.RESPONSE_MESSAGE_CREATED +" game marked as finished.", response = HttpStatus.class),
            @ApiResponse(code = 400, message = APIDoc.RESPONSE_MESSAGE_BAD_REQUEST + " Invalid ID supplied"),
            @ApiResponse(code = 404, message = APIDoc.RESPONSE_MESSAGE_NOT_FOUND+ " Game not found") }
    )
    @PostMapping(
            value = "/game/{gameId}/end",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    ResponseEntity<HttpStatus> finishGame(
            @ApiParam(value = "ID of the game to finish",required=true)
            @PathVariable("gameId")
            Long gameId
    );

    @ApiOperation(
            value = "Get a question",
            nickname = "getGameById",
            notes = "When the game is not closed, returns the same data as the\n" +
                    "endpoint POST /game. When the game is closed, the GameQuestion list with all\n" +
                    "the information is included in the response to compute the statistics (see “final\n" +
                    "screen in Frontend section)",
            response = GameDTO.class,
            tags={ MATCH_TAG }
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = APIDoc.RESPONSE_MESSAGE_OK +" game found", response = GameDTO.class),
            @ApiResponse(code = 400, message = APIDoc.RESPONSE_MESSAGE_BAD_REQUEST +"Invalid ID supplied"),
            @ApiResponse(code = 404, message = APIDoc.RESPONSE_MESSAGE_NOT_FOUND + "Game not found") }
    )
    @GetMapping(
            value = "/game/{gameId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    ResponseEntity<GameDTO> getGameById(
            @ApiParam(value = "ID of the game to return",required=true)
            @PathVariable("gameId")
            Long gameId
    );

    @ApiOperation(
            value = "Create a new game.",
            nickname = "createNewGame",
            notes = "Creates a new game and returns it.",
            response = GameDTO.class,
            tags={ MATCH_TAG }
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = APIDoc.RESPONSE_MESSAGE_CREATED +" new game created", response = GameDTO.class),
            @ApiResponse(code = 400, message = APIDoc.RESPONSE_MESSAGE_BAD_REQUEST +" Invalid ID supplied")
    })
    @PostMapping(
            value = "/game",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    ResponseEntity<GameDTO> createNewGame(
            @ApiParam(value = "Game config", required = true)
            @RequestBody
            GameConfigRequest gameConfig
    );

}
