package com.example.trivia.entity;

import com.example.trivia.model.Game;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Game Question Entity
 */
@Getter
@Setter
@Entity
@Table(name = "game_question")
public class GameQuestionEntity {

    /**
     * Game Question ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_question_id", unique = true, nullable = false)
    private Long id;

    /**
     * Game
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private GameEntity game;

    /**
     * Question
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    /**
     * Question order
     */
    @Column(name = "question_order", nullable = false)
    private Integer questionOrder;

    /**
     * Chosen answer
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chosen_answer_id", nullable = true)
    private AnswerEntity chosenAnswer;

}
