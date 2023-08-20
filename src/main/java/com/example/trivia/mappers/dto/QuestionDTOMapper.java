package com.example.trivia.mappers.dto;

import com.example.trivia.api.v1.dto.QuestionDTO;
import com.example.trivia.model.Answer;
import com.example.trivia.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Question DTO Mapper
 */
@Component
public class QuestionDTOMapper {

    /**
     * Answer DTO Mapper
     */
    @Autowired
    AnswerDTOMapper answerDTOMapper;

    /**
     * Map Question to QuestionDTO
     *
     * @param model Question
     * @return QuestionDTO
     */
    public QuestionDTO map (Question model){
        QuestionDTO questionInfoResponse = new QuestionDTO();
        questionInfoResponse.setText(model.getText());
        questionInfoResponse.setAnswers(model.getAnswers().stream().map(answerDTOMapper::map).collect(Collectors.toList()));
        questionInfoResponse.setCategory(model.getCategory().getCategoryName());
        questionInfoResponse.setId(model.getId());
//        questionInfoResponse.setApiId(model.getApiId());
        if(model.getChosenAnswer() != null)
            questionInfoResponse.setChosenAnswer(answerDTOMapper.map(model.getChosenAnswer()));
        return questionInfoResponse;
    }
}
