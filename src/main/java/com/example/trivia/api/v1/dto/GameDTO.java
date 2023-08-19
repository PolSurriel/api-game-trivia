package com.example.trivia.api.v1.dto;

import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import lombok.Data;

import java.util.List;

@Data
public class GameDTO {
    private Long id;
    private String difficulty;
    private Boolean finished;
    private List<QuestionDTO> questions;
    private List<String> categories;
}
