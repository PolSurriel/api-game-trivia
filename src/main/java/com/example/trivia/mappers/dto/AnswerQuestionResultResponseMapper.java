package com.example.trivia.mappers.dto;

import com.example.trivia.api.v1.dto.response.AnswerQuestionResultResponse;
import com.example.trivia.model.AnswerQuestionResult;
import org.springframework.stereotype.Component;

/**
 * Answer Question Result Response Mapper
 */
@Component
public class AnswerQuestionResultResponseMapper {
    /**
     * Map Answer Question Result to Answer Question Result Response
     *
     * @param model Answer Question Result
     * @return Answer Question Result Response
     */
    public AnswerQuestionResultResponse map(AnswerQuestionResult model){
        AnswerQuestionResultResponse answerQuestionResultResponse = new AnswerQuestionResultResponse();
        answerQuestionResultResponse.setCorrectAnswerId(model.getCorrectAnswerId());
        return answerQuestionResultResponse;
    }
}
