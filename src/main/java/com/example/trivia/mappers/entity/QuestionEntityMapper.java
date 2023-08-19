package com.example.trivia.mappers.entity;

import com.example.trivia.entity.AnswerEntity;
import com.example.trivia.entity.QuestionEntity;
import com.example.trivia.model.Answer;
import com.example.trivia.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionEntityMapper {

    @Autowired
    AnswerEntityMapper answerEntityMapper;

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
