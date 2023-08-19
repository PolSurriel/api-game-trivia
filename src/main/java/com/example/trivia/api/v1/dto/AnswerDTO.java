package com.example.trivia.api.v1.dto;

import lombok.Data;

/**
 * Answer DTO
 */
@Data
public class AnswerDTO {
    /**
     * Answer ID
     */
    private Long id;
    /**
     * Answer text
     */
    private String text;

}
