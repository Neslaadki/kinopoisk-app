package com.andersen.dev.kinopoiskapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentDto {

    private Long id;
    private String name;
    private String description;
    private String typeName;
    private String genreName;

}
