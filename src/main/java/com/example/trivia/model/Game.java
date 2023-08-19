package com.example.trivia.model;

import com.example.trivia.entity.GameQuestionEntity;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
/**
 * Game
 */
@Data
public class Game {
    /**
     * Game id
     */
    private Long id;
    /**
     * Game difficulty
     */
    private GameDifficulty difficulty;
    /**
     * Game finished
     */
    private Boolean finished;
    /**
     * Game questions
     */
    private List<Question> questions;
    /**
     * Game categories
     */
    private List<GameCategory> categories;
}
