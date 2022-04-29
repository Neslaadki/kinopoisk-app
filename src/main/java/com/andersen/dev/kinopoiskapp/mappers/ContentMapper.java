package com.andersen.dev.kinopoiskapp.mappers;

import com.andersen.dev.kinopoiskapp.dto.ContentDto;
import com.andersen.dev.kinopoiskapp.model.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContentMapper {

    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    @Mappings({
            @Mapping(source = "contentType.name", target = "typeName"),
            @Mapping(source = "genre.name", target = "genreName")
    })
    ContentDto toDTO(Content content);

}