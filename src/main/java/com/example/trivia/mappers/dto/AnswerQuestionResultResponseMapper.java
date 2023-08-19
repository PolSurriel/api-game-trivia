package com.example.trivia.mappers.dto;

import com.example.trivia.api.v1.dto.response.AnswerQuestionResultResponse;
import com.example.trivia.model.AnswerQuestionResult;
import org.springframework.stereotype.Component;

@Component
public class AnswerQuestionResultResponseMapper {
    public AnswerQuestionResultResponse map(AnswerQuestionResult model){
        AnswerQuestionResultResponse answerQuestionResultResponse = new AnswerQuestionResultResponse();
        answerQuestionResultResponse.setCorrectAnswerId(model.getCorrectAnswerId());
        return answerQuestionResultResponse;
    }
}
