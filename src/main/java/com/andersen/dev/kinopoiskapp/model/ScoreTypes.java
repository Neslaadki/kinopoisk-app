package com.andersen.dev.kinopoiskapp.model;

import lombok.Getter;

@Getter
public enum ScoreTypes {
    NEGATIVE("negative"),
    NEUTRAL("neutral"),
    POSITIVE("positive");

    private final String name;

    ScoreTypes(String name) {
        this.name = name;
    }

    public static ScoreTypes getByName(String name) {
        try {
            return ScoreTypes.valueOf(name.trim().toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
