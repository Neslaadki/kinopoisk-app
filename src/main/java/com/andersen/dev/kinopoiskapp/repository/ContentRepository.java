package com.andersen.dev.kinopoiskapp.repository;

import com.andersen.dev.kinopoiskapp.model.Content;
import com.andersen.dev.kinopoiskapp.model.ContentTypes;
import com.andersen.dev.kinopoiskapp.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> getContentByContentType(ContentTypes contentType);

    List<Content> getContentByGenre(Genres genre);

    List<Content> getContentByGenreAndContentType(Genres genre, ContentTypes contentTypes);

    Content getById(Long id);
}
