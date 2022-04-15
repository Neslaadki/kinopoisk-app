package com.andersen.dev.kinopoiskapp.mappers;

import com.andersen.dev.kinopoiskapp.dto.ContentTypesDto;
import com.andersen.dev.kinopoiskapp.model.ContentTypes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContentTypesMapper {

    ContentTypesMapper INSTANCE = Mappers.getMapper(ContentTypesMapper.class);

    ContentTypesDto toDTO(ContentTypes contentTypes);

}
