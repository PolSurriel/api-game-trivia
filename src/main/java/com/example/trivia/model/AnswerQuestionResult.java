package com.example.trivia.model;

import lombok.Data;

@Data
public class AnswerQuestionResult {
    private Boolean isCorrect;
    private Long correctAnswerId;
    private Integer correctAnswerIndex;
}
