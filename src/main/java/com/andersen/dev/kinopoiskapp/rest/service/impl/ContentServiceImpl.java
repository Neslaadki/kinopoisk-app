package com.andersen.dev.kinopoiskapp.rest.service.impl;

import com.andersen.dev.kinopoiskapp.dto.ContentDto;
import com.andersen.dev.kinopoiskapp.dto.ContentTypesDto;
import com.andersen.dev.kinopoiskapp.dto.GenresDto;
import com.andersen.dev.kinopoiskapp.exceptions.InvalidRequestData;
import com.andersen.dev.kinopoiskapp.mappers.ContentMapper;
import com.andersen.dev.kinopoiskapp.mappers.ContentTypesMapper;
import com.andersen.dev.kinopoiskapp.mappers.GenresMapper;
import com.andersen.dev.kinopoiskapp.model.ContentTypes;
import com.andersen.dev.kinopoiskapp.model.Genres;
import com.andersen.dev.kinopoiskapp.repository.ContentRepository;
import com.andersen.dev.kinopoiskapp.rest.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    private final ContentTypesMapper contentTypesMapper;
    private final GenresMapper genresMapper;
    private final ContentMapper contentMapper;

    private Map<Integer, String> genresMap;
    private Map<Integer, String> typesMap;

    @PostConstruct
    private void createMaps() {
        genresMap = Arrays.stream(Genres.values()).collect(Collectors.toMap(Genres::getId, Genres::getName));
        typesMap = Arrays.stream(ContentTypes.values()).collect(Collectors.toMap(ContentTypes::getId, ContentTypes::getName));
    }


    @Override
    public ContentDto getContentById(Long id) {
        return contentMapper.toDTO(contentRepository.getById(id));
    }

    @Override
    public List<ContentTypesDto> getContentTypes() {
        return Arrays.stream(ContentTypes.values())
                .map(contentTypesMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<GenresDto> getGenres() {
        return Arrays.stream(Genres.values())
                .map(genresMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ContentDto> getContents() {
        return contentRepository.findAll()
                .stream().map(contentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ContentDto> getContentsByContendTypeId(Integer id) {
        try {
            return contentRepository.getContentByContentType(ContentTypes.getByName(typesMap.get(id)))
                    .stream().map(contentMapper::toDTO).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new InvalidRequestData("Param has invalid value");
        }
    }

    @Override
    public List<ContentDto> getContentsByGenreId(Integer id) {
        try {
            return contentRepository.getContentByGenre(Genres.getGenreByName(genresMap.get(id)))
                    .stream().map(contentMapper::toDTO).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new InvalidRequestData("Param has invalid value");
        }
    }


    @Override
    public List<ContentDto> getContentsByGenreIdAndTypeId(Integer genreId, Integer typeId) {
        try {
            return contentRepository.getContentByGenreAndContentType(Genres.getGenreByName(genresMap.get(genreId)),
                            ContentTypes.getByName(typesMap.get(typeId)))
                    .stream().map(contentMapper::toDTO).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new InvalidRequestData("Params has invalid values");
        }

    }
}
