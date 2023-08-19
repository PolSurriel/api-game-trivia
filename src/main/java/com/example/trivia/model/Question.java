package com.example.trivia.model;

import com.example.trivia.entity.AnswerEntity;
import com.example.trivia.model.enums.GameCategory;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
/**
 * Question
 */
@Data
public class Question {
    /**
     * Question id
     */
    private Long id;
    /**
     * Question trivia api id
     */
    private String apiId;
    /**
     * Question category
     */
    private GameCategory category;
    /**
     * Question text
     */
    private String text;
    /**
     * Question answers
     */
    private List<Answer> answers;

}
