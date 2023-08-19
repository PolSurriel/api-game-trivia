package com.example.trivia.model;

import com.example.trivia.entity.GameQuestionEntity;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class Game {
    private Long id;
    private GameDifficulty difficulty;
    private Boolean finished;
    private List<Question> questions;
    private List<GameCategory> categories;
}
