package com.example.trivia.mappers.dto;

import com.example.trivia.api.v1.dto.GameDTO;
import com.example.trivia.model.Game;
import com.example.trivia.model.enums.GameCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Game DTO Mapper
 */
@Component
public class GameDTOMapper {

    /**
     * Question DTO Mapper
     */
    @Autowired
    private QuestionDTOMapper questionDTOMapper;

    @Autowired
    private AnswerDTOMapper answerDTOMapper;

    /**
     * Map Game to GameDTO
     *
     * @param model Game
     * @return GameDTO
     */
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

//        if(model.getFinished()){
            for (int i = 0; i < gameDTO.getQuestions().size(); i++) {
                var modelCorrectAnswer = model.getQuestions().get(i).getCorrectAnswer();
                var modelChosenAnswer = model.getQuestions().get(i).getChosenAnswer();
                if(modelChosenAnswer != null && modelCorrectAnswer != null)
                    gameDTO.getQuestions()
                        .get(i)
                        .setCorrectAnswer(answerDTOMapper.map(modelCorrectAnswer));
            }
//        }

        return gameDTO;
    }

}
