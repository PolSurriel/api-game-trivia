package com.example.trivia.api.v1.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Game configuration request
 */
@ApiModel(description = "Game configuration request")
@Data
public class GameConfigRequest {
    /**
     * Number of total questions.
     */
    @ApiModelProperty(value = "Number of total questions.", example = "5")
    @JsonProperty("number_of_questions")
    Integer numberOfQuestions;

    /**
     * Difficulty of the match.
     */
    @ApiModelProperty(value = "Difficulty of the match.", allowableValues = "easy, medium, hard", example = "easy")
    @JsonProperty("difficulty")
    String difficulty;

    /**
     * Categories (topic) of the questions.
     */
    static final String ALLOWED_CATEGORIES = "science,film_and_tv,music,history,geography,art_and_literature,sport_and_leisure,general_knowledge,science,food_and_drink";
    @ApiModelProperty(value = "Categories (topic) of the questions.", allowableValues = ALLOWED_CATEGORIES, example = "science")
    @JsonProperty("categories")
    List<String> categories;
}
