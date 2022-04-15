package com.andersen.dev.kinopoiskapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDto {

    private Long id;
    private Long userId;
    private Long contentId;
    private String topic;
    private String text;
    private String scoreType;
    private Date created;
    private Date updated;

}
