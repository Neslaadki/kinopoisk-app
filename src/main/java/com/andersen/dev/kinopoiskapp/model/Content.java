package com.andersen.dev.kinopoiskapp.model;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "contents")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Content extends BaseEntity {

    private String name;
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ContentTypes contentType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genres genre;

}
