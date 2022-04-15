package com.andersen.dev.kinopoiskapp.model;

import lombok.Getter;

@Getter
public enum ContentTypes {
    FILM(1, "Film"), SERIAL(2, "Serial");

    private final int id;
    private final String name;

    ContentTypes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ContentTypes getByName(String name) {
        try {
            return ContentTypes.valueOf(name.trim().toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

}
