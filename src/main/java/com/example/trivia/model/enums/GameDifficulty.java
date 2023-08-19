package com.example.trivia.model.enums;

public enum GameDifficulty {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private final String difficultyLevel;

    GameDifficulty(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public static GameDifficulty fromString(String value) {
        for (GameDifficulty difficulty : GameDifficulty.values()) {
            if (difficulty.getDifficultyLevel().equalsIgnoreCase(value)) {
                return difficulty;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }
}