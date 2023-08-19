package com.example.trivia.entity;

import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Game Entity
 */
@Getter
@Setter
@Entity
@Table(name = "game")
public class GameEntity {
    /**
     * Game ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", unique = true, nullable = false)
    private Long id;

    /**
     * Game difficulty
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private GameDifficulty difficulty;

    /**
     * Game is finished
     */
    @Column(name = "finished", nullable = false)
    private Boolean finished;

    /**
     * Game questions
     */
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameQuestionEntity> questions;

    /**
     * Game categories
     */
    @ElementCollection(targetClass = GameCategory.class)
    @CollectionTable(name = "game_category", joinColumns = @JoinColumn(name = "game_id"))
    @Enumerated(EnumType.STRING)
    private List<GameCategory> categories;

}
