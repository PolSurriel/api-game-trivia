package com.example.trivia.client.dto;

import lombok.Data;
import java.util.List;

@Data
public class TriviaQuestionDTO {

    @Data
    public static class QuestionText{
        private String text;
    }

    private String category;
    private String id;
    private String correctAnswer;
    private List<String> incorrectAnswers;
    private QuestionText question;
    private List<String> tags;
    private String type;
    private String difficulty;
    private List<String> regions;
    private Boolean isNiche;

}

