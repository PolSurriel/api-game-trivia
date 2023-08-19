package com.example.trivia.mappers.entity;

import com.example.trivia.entity.AnswerEntity;
import com.example.trivia.entity.QuestionEntity;
import com.example.trivia.model.Answer;
import com.example.trivia.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * Question Entity Mapper
 */
@Component
public class QuestionEntityMapper {

    /**
     * Answer Entity Mapper
     */
    @Autowired
    AnswerEntityMapper answerEntityMapper;

    /**
     * Map Question to Question Entity
     *
     * @param model Question
     * @return Question Entity
     */
    public QuestionEntity map (Question model){
        QuestionEntity result = new QuestionEntity();
        result.setApiId(model.getApiId());
        result.setCategory(model.getCategory());
        result.setText(model.getText());

        List<AnswerEntity> answerList = model.getAnswers()
                .stream()
                .map(answerEntityMapper::map)
                .toList();

        result.setAnswers(answerList);
        return result;
    }

}
