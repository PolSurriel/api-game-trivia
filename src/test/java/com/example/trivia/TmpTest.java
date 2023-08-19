package com.example.trivia;

import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import org.junit.jupiter.api.Test;
public class TmpTest {

    @Test
    public void tmpTest() throws Exception {
        GameDifficulty gameDifficulty = GameDifficulty.EASY;
        GameCategory gameCategory = GameCategory.FILM_AND_TV;

        System.out.println(gameDifficulty.getDifficultyLevel());
        System.out.println(gameDifficulty.name());
    }


}
