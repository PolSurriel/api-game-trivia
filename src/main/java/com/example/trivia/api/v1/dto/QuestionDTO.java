package com.example.trivia.api.v1.dto;

import com.example.trivia.api.v1.dto.AnswerDTO;
import com.example.trivia.model.enums.GameCategory;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private String apiId;
    private String category;
    private String text;
    private List<AnswerDTO> answers;
}
