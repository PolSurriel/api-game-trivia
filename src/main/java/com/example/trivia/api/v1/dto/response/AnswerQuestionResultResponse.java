package com.example.trivia.api.v1.dto.response;

import lombok.Data;

/**
 * Answer question result response
 */
@Data
public class AnswerQuestionResultResponse {
    /**
     * The Id of the correct answer.
     */
    private Long correctAnswerId;
}
