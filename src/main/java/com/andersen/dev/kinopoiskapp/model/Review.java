package com.andersen.dev.kinopoiskapp.model;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Review extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "contentId")
    private Content content;

    @NotNull
    private String topic;

    private String text;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ScoreTypes scoreTypes;

}