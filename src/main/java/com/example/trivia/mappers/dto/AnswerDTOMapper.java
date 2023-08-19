package com.example.trivia.mappers.dto;

import com.example.trivia.api.v1.dto.AnswerDTO;
import com.example.trivia.model.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerDTOMapper {
    public AnswerDTO map (Answer model){
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setText(model.getText());
        answerDTO.setId(model.getId());
        return answerDTO;
    }
}
