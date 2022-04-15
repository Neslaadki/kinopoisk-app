package com.andersen.dev.kinopoiskapp.model;

import lombok.Getter;

@Getter
public enum Genres {
    ACTION(1, "Action"),
    COMEDY(2, "Comedy"),
    DRAMA(3, "Drama"),
    FANTASY(4, "Fantasy"),
    HORROR(5, "Horror"),
    MYSTERY(6, "Mystery"),
    THRILLER(7, "Thriller"),
    ROMANCE(8, "Romance"),
    WESTERN(9, "Western");

    private final Integer id;
    private final String name;

    Genres(int id, String name) {
        this.id = id;
        this.name = name;

    }


    public static Genres getGenreByName(String name) {
        try {
            return Genres.valueOf(name.trim().toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

}