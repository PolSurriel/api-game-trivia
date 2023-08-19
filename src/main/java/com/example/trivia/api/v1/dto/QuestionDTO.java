package com.example.trivia.api.v1.dto;

import com.example.trivia.api.v1.dto.AnswerDTO;
import com.example.trivia.model.enums.GameCategory;
import lombok.Data;

import java.util.List;

/**
 * Question DTO
 */
@Data
public class QuestionDTO {
    /**
     * Question ID
     */
    private Long id;

//    /**
//     * Question Trivia API ID
//     */
//    private String apiId;

    /**
     * Question category
     */
    private String category;

    /**
     * Question difficulty
     */
    private String text;

    /**
     * Question difficulty
     */
    private List<AnswerDTO> answers;
}
