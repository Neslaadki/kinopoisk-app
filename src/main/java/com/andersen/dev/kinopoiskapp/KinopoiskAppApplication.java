package com.andersen.dev.kinopoiskapp;

import com.andersen.dev.kinopoiskapp.exceptions.EntitiesIsAlreadyUploaded;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KinopoiskAppApplication {

    public static void main(String[] args) throws EntitiesIsAlreadyUploaded {
        SpringApplication.run(KinopoiskAppApplication.class, args);
    }

}
