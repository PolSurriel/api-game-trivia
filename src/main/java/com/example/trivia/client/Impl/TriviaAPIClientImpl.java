package com.example.trivia.client.Impl;

import com.example.trivia.client.TriviaAPIClient;
import com.example.trivia.client.dto.TriviaQuestionDTO;
import com.example.trivia.mappers.model.QuestionMapper;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Trivia API Client Implementation
 * Contains the implementation of the Trivia API Client to perform requests to the Trivia API.
 *
 * @see TriviaAPIClient
 */
@Component
public class TriviaAPIClientImpl implements TriviaAPIClient {
    /**
     * Web client
     */
    @Autowired
    WebClient webClient;

    /**
     * Trivia API Base URL
     */
    @Value("${trivia.api.url}")
    private String triviaAPIBaseURL;

    /**
     * Question mapper
     */
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * Endpoint to obtain questions by category and difficulty
     */
    private final String ENDPOINT_QUESTIONS_BY_CATEGORY_AND_DIFFICULTY = "questions";

    /**
     * Obtain random questions by category and difficulty
     *
     * @param amount     Amount of questions to obtain
     * @param categories Categories to filter
     * @param difficulty Difficulty to filter
     * @return Questions
     */
    @Override
    public Flux<Question> obtainQuestions(Integer amount, List<GameCategory> categories, GameDifficulty difficulty){

        final String url = triviaAPIBaseURL + ENDPOINT_QUESTIONS_BY_CATEGORY_AND_DIFFICULTY;

        final String categoriesParam = String.join(",", categories.stream()
                .map(GameCategory::getCategoryName)
                .collect(Collectors.toList()));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("limit", amount)
                .queryParam("categories", categoriesParam)
                .queryParam("difficulties", difficulty.getDifficultyLevel());

        URI finalUri = builder.build().toUri();

        return webClient.get()
                .uri(finalUri)
                .retrieve()
                .bodyToFlux(TriviaQuestionDTO.class)
                .map(questionMapper::map);
    }

}
