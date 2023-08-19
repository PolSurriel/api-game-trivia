package com.example.trivia.model;

import com.example.trivia.entity.AnswerEntity;
import com.example.trivia.model.enums.GameCategory;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class Question {
    private Long id;
    private String apiId;
    private GameCategory category;
    private String text;
    private List<Answer> answers;

}
