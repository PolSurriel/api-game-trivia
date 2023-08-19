package com.example.trivia.mappers.model;

import com.example.trivia.client.dto.TriviaQuestionDTO;
import com.example.trivia.entity.QuestionEntity;
import com.example.trivia.model.Answer;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class QuestionMapper {

    @Autowired
    AnswerMapper answerMapper;

    public Question map(QuestionEntity entity){
        Question result = new Question();
        result.setId(entity.getId());
        result.setApiId(entity.getApiId());
        result.setCategory(entity.getCategory());
        result.setText(entity.getText());
        result.setAnswers(entity.getAnswers()
                .stream()
                .map(answerMapper::map)
                .collect(java.util.stream.Collectors
                .toList()
                )
        );
        return result;
    }

    public Question map(TriviaQuestionDTO dto){
        Question result = new Question();
        result.setApiId(dto.getId());

        result.setCategory(GameCategory.fromString(dto.getCategory()));
        result.setText(dto.getQuestion().getText());

        List<Answer> answers = Stream.concat(
                dto.getIncorrectAnswers()
                    .stream()
                    .map(answer ->new Answer(-1L, answer, Boolean.FALSE)),
                Stream.of(new Answer(-1L, dto.getCorrectAnswer(), Boolean.TRUE))
            ).toList();

        result.setAnswers(answers);
        return result;
    }

}
