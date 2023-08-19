package com.example.trivia.service;

import com.example.trivia.api.v1.dto.response.AnswerQuestionResultResponse;
import com.example.trivia.model.AnswerQuestionResult;
import com.example.trivia.model.Game;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;

import java.util.List;

public interface MatchService {

    Game getGameById(Long gameId);
    Game createGame(Integer numberOfQuestions, GameDifficulty difficulty, List<GameCategory> category);
    AnswerQuestionResult submitAnswer(Long gameId, Integer questionIndex, Long answerId);

    Question getQuestionInfo(Long gameId, Integer questionIndex);
    void finishFame(Long gameId);


}
