package com.example.trivia.mappers.model;

import com.example.trivia.entity.GameEntity;
import com.example.trivia.entity.GameQuestionEntity;
import com.example.trivia.model.Game;
import com.example.trivia.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Game Mapper
 */
@Component
public class GameMapper {

    /**
     * Question Entity Mapper
     */
    @Autowired
    QuestionMapper questionEntityMapper;

    /** Map Game Entity to Game
     *
     * @param entity Game Entity
     * @return Game
     */
    public Game map (GameEntity entity){
        Game result = new Game();
        result.setId(entity.getId());
        result.setDifficulty(entity.getDifficulty());
        result.setFinished(entity.getFinished());
        List<Question> questions = entity.getQuestions()
                .stream()
                .map(GameQuestionEntity::getQuestion)
                .map(questionEntityMapper::map)
                .toList();
        result.setQuestions(questions);
        result.setCategories(entity.getCategories());
        return result;
    }
}
