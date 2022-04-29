package com.andersen.dev.kinopoiskapp.mappers;

import com.andersen.dev.kinopoiskapp.dto.ReviewDto;
import com.andersen.dev.kinopoiskapp.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "content.id", target = "contentId"),
            @Mapping(source = "scoreTypes.name", target = "scoreType")
    })
    ReviewDto toDTO(Review review);


}
