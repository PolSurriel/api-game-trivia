package com.example.trivia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Answer
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Answer {
    /**
     * Answer id
     */
    private Long id;
    /**
     * Answer text
     */
    private String text;
    /**
     * Answer correct
     */
    private Boolean correct;
}
