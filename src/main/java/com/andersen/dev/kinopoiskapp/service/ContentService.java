package com.andersen.dev.kinopoiskapp.service;


import com.andersen.dev.kinopoiskapp.dto.ContentDto;
import com.andersen.dev.kinopoiskapp.dto.ContentTypesDto;
import com.andersen.dev.kinopoiskapp.dto.GenresDto;

import java.util.List;

public interface ContentService {

    ContentDto getContentById(Long id);

    List<ContentTypesDto> getContentTypes();

    List<GenresDto> getGenres();

    List<ContentDto> getContentsByGenreIdAndTypeId(Integer genreId, Integer typeId);

    List<ContentDto> getContentsByContendTypeId(Integer id);

    List<ContentDto> getContentsByGenreId(Integer id);

    List<ContentDto> getContents();

}
