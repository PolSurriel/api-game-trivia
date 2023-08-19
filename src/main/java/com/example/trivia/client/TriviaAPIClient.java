package com.example.trivia.client;

import com.example.trivia.model.Game;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * Trivia API Client Interface
 */
public interface TriviaAPIClient {
    /**
     * Obtain random questions by category and difficulty
     *
     * @param amount     Amount of questions to obtain
     * @param catergories Categories to filter
     * @param difficulty Difficulty to filter
     * @return Questions
     */
    Flux<Question> obtainQuestions(Integer amount, List<GameCategory> catergories, GameDifficulty difficulty);

}
