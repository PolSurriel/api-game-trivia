package com.example.trivia.mappers.model;

import com.example.trivia.entity.AnswerEntity;
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

    @Autowired
    AnswerMapper answerMapper;

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

        // Add chosen answer id to each question
        entity.getQuestions().forEach(gameQuestionEntity -> {
            questions.stream().filter(question -> question.getId().equals(gameQuestionEntity.getQuestion().getId())).findFirst().ifPresent(question -> {
                var entityChosenAnswer = gameQuestionEntity.getChosenAnswer();
                if(entityChosenAnswer != null)
                    question.setChosenAnswer(
                            answerMapper.map(entityChosenAnswer)
                    );
            });
        });

        entity.getQuestions().forEach(gameQuestionEntity -> {
            questions.stream().filter(question -> question.getId().equals(gameQuestionEntity.getQuestion().getId())).findFirst().ifPresent(question -> {
                gameQuestionEntity.getQuestion().getAnswers().stream().filter(AnswerEntity::getCorrect).findFirst().ifPresent(answerEntity -> {
                    question.setCorrectAnswer(
                            answerMapper.map(answerEntity)
                    );
                });
            });
        });




        result.setQuestions(questions);
        result.setCategories(entity.getCategories());
        return result;
    }
}
