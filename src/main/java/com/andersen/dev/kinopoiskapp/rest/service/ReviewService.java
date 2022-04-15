package com.andersen.dev.kinopoiskapp.rest.service;

import com.andersen.dev.kinopoiskapp.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    Long saveReview(ReviewDto r);

    List<ReviewDto> getReviewByContent(Long contentId);

    List<ReviewDto> getReviewByClient(Long clientId);

}
