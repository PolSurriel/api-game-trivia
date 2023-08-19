package com.example.trivia.service;

import com.example.trivia.api.v1.dto.response.AnswerQuestionResultResponse;
import com.example.trivia.model.AnswerQuestionResult;
import com.example.trivia.model.Game;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;

import java.util.List;

/**
 * Match Service
 */
public interface MatchService {

    /**
     * Get game by id
     *
     * @param gameId game id
     * @return game
     */
    Game getGameById(Long gameId);

    /**
     * Create game
     *
     * @param numberOfQuestions number of questions
     * @param difficulty        difficulty
     * @param category          category
     * @return game
     */
    Game createGame(Integer numberOfQuestions, GameDifficulty difficulty, List<GameCategory> category);
    /**
     * Submit answer and get the correct answer ID
     *
     * @param gameId       game id
     * @param questionIndex question index
     * @param answerId      answer id
     * @return answer question result
     */
    AnswerQuestionResult submitAnswer(Long gameId, Integer questionIndex, Long answerId);

    /**
     * Get question info
     *
     * @param gameId       game id
     * @param questionIndex question index
     * @return question
     */
    Question getQuestionInfo(Long gameId, Integer questionIndex);

    /**
     * Mark game as finished
     *
     * @param gameId       game id
     * @return answer question result
     */
    void finishFame(Long gameId);


}
