package com.example.trivia.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
/**
 * Answer Entity
 */
@Entity
@Getter
@Setter
@Table(name = "answer")
public class AnswerEntity {
    /**
     * Answer ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", unique = true, nullable = false)
    private Long id;

    /**
     * Question
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    /**
     * Answer text
     */
    private String text;

    /**
     * Answer is correct
     */
    private Boolean correct;
}
