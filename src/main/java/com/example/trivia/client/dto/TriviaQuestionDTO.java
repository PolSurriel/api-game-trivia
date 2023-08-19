package com.example.trivia.client.dto;

import lombok.Data;
import java.util.List;

/**
 * Trivia Question DTO
 */
@Data
public class TriviaQuestionDTO {

    /**
     * Question text
     */
    @Data
    public static class QuestionText{
        private String text;
    }

    /**
     * Question category
     */
    private String category;
    /**
     * Question ID
     */
    private String id;
    /**
     * Question correct answer
     */
    private String correctAnswer;
    /**
     * Question incorrect answers
     */
    private List<String> incorrectAnswers;
    /**
     * Question text
     */
    private QuestionText question;
    /**
     * Question tags
     */
    private List<String> tags;
    /**
     * Question type
     */
    private String type;
    /**
     * Question difficulty
     */
    private String difficulty;
    /**
     * Question regions
     */
    private List<String> regions;
    /**
     * Question is niche
     */
    private Boolean isNiche;

}

