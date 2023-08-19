package com.example.trivia.mappers.dto;

import com.example.trivia.api.v1.dto.GameDTO;
import com.example.trivia.model.Game;
import com.example.trivia.model.enums.GameCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameDTOMapper {

    @Autowired
    private QuestionDTOMapper questionDTOMapper;

    public GameDTO map(Game model){
        GameDTO gameDTO = new GameDTO();
        gameDTO.setCategories(model.getCategories()
                .stream()
                .map(GameCategory::getCategoryName)
                .collect(java.util.stream.Collectors.toList())
        );
        gameDTO.setDifficulty(model.getDifficulty().getDifficultyLevel());
        gameDTO.setFinished(model.getFinished());
        gameDTO.setId(model.getId());
        gameDTO.setQuestions(model.getQuestions().stream().map(questionDTOMapper::map).collect(java.util.stream.Collectors.toList()));
        return gameDTO;
    }

}