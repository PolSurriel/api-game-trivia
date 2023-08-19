package com.example.trivia.entity;

import com.example.trivia.model.enums.GameCategory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "api_id", nullable = false)
    private String apiId;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private GameCategory category;

    @Column(name = "text", nullable = false)
    private String text;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<AnswerEntity> answers;

}
