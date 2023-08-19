package com.example.trivia.api.v1.dto;

import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import lombok.Data;

import java.util.List;

/**
 * Game DTO
 */
@Data
public class GameDTO {
    /**
     * Game ID
     */
    private Long id;
    /**
     * Game difficulty
     */
    private String difficulty;
    /**
     * Game finished
     */
    private Boolean finished;
    /**
     * Game questions
     */
    private List<QuestionDTO> questions;
    /**
     * Game categories
     * */
    private List<String> categories;
}
