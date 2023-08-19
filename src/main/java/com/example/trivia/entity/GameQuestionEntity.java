package com.example.trivia.entity;

import com.example.trivia.model.Game;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "game_question")
public class GameQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_question_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @Column(name = "question_order", nullable = false)
    private Integer questionOrder;

    @ManyToOne
    @JoinColumn(name = "chosen_answer_id", nullable = true)
    private AnswerEntity chosenAnswer;

}
