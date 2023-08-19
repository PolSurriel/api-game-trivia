package com.example.trivia.mappers.model;

import com.example.trivia.entity.AnswerEntity;
import com.example.trivia.model.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    Answer map(AnswerEntity entity){
        Answer result = new Answer();
        result.setText(entity.getText());
        result.setCorrect(entity.getCorrect());
        result.setId(entity.getId());
        return result;
    }

}
