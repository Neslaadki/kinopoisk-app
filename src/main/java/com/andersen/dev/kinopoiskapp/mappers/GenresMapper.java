package com.andersen.dev.kinopoiskapp.mappers;

import com.andersen.dev.kinopoiskapp.dto.GenresDto;
import com.andersen.dev.kinopoiskapp.model.Genres;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenresMapper {

    GenresMapper INSTANCE = Mappers.getMapper(GenresMapper.class);

    GenresDto toDTO(Genres genres);

}