package com.andersen.dev.kinopoiskapp.mappers;

import com.andersen.dev.kinopoiskapp.dto.RegistrationRequestDto;
import com.andersen.dev.kinopoiskapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegistrationRequestMapper {

    RegistrationRequestMapper INSTANCE = Mappers.getMapper(RegistrationRequestMapper.class);

    User fromDto(RegistrationRequestDto requestDto);

}
