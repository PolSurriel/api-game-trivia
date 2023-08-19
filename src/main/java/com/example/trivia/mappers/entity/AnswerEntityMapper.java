package com.example.trivia.mappers.entity;

import com.example.trivia.entity.AnswerEntity;
import com.example.trivia.model.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerEntityMapper {

    public AnswerEntity map(Answer model){
        AnswerEntity result = new AnswerEntity();
        result.setText(model.getText());
        result.setCorrect(model.getCorrect());
        return result;
    }

}
