package com.example.trivia.model.enums;

/**
 * Game Category enum.
 *
 * BE CAREFUL: The enum values are the trivia api possible values, do not change them.
 */
public enum GameCategory {
    MUSIC("music"),
    SPORT_AND_LEISURE("sport_and_leisure"),
    FILM_AND_TV("film_and_tv"),
    ARTS_AND_LITERATURE("arts_and_literature"),
    HISTORY("history"),
    SOCIETY_AND_CULTURE("society_and_culture"),
    SCIENCE("science"),
    GEOGRAPHY("geography"),
    FOOD_AND_DRINK("food_and_drink"),
    GENERAL_KNOWLEDGE("general_knowledge");

    private final String categoryName;

    GameCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public static GameCategory fromString(String value) {
        for (GameCategory category : GameCategory.values()) {
            if (category.getCategoryName().equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }

}
