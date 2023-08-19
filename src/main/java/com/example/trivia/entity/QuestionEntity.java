package com.example.trivia.entity;

import com.example.trivia.model.enums.GameCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Question Entity
 */
@Getter
@Setter
@Entity
@Table(name = "question")
public class QuestionEntity {
    /**
     * Question ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", unique = true, nullable = false)
    private Long id;

    /**
     * Question API ID
     */
    @Column(name = "api_id", nullable = false)
    private String apiId;
    /**
     * Question category
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private GameCategory category;

    /**
     * Question difficulty
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * Question answers
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<AnswerEntity> answers;

}
