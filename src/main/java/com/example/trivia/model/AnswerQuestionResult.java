package com.example.trivia.model;

import lombok.Data;
/**
 * Answer Question Result
 */
@Data
public class AnswerQuestionResult {
    /**
     * Is correct
     */
    private Boolean isCorrect;
    /**
     * Correct answer id
     */
    private Long correctAnswerId;
    /**
     * Correct answer index
     */
    private Integer correctAnswerIndex;
}
