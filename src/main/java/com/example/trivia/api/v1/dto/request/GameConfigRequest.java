package com.example.trivia.api.v1.dto.request;

import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel(description = "Game configuration request")
@Data
public class GameConfigRequest {
    @JsonProperty("number_of_questions")
    Integer numberOfQuestions;

    @JsonProperty("difficulty")
    String difficulty;

    @JsonProperty("categories")
    List<String> categories;
}
