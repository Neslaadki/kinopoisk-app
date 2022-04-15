package com.andersen.dev.kinopoiskapp.service.impl;

import com.andersen.dev.kinopoiskapp.dto.ReviewDto;
import com.andersen.dev.kinopoiskapp.exceptions.ContentNotFoundException;
import com.andersen.dev.kinopoiskapp.mappers.ReviewMapper;
import com.andersen.dev.kinopoiskapp.model.*;
import com.andersen.dev.kinopoiskapp.repository.ContentRepository;
import com.andersen.dev.kinopoiskapp.repository.ReviewRepository;
import com.andersen.dev.kinopoiskapp.service.UserService;
import com.andersen.dev.kinopoiskapp.security.jwt.JwtAuthException;
import com.andersen.dev.kinopoiskapp.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {


    private final ReviewRepository reviewRepository;
    private final ContentRepository contentRepository;
    private final ContentServiceImpl contentServiceImpl;
    private final ReviewMapper reviewMapper;
    private final UserService userService;


    @Override
    public Long saveReview(ReviewDto r) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        if (user!= null){

            Content content = contentRepository.getById(r.getContentId());
            if (content != null){
                Review rv = new Review()
                        .setUser(user)
                        .setContent(content)
                        .setTopic(r.getTopic())
                        .setText(r.getText())
                        .setScoreTypes(ScoreTypes.getByName(r.getScoreType()));
                rv.setStatus(Status.ACTIVE);
                Review review = reviewRepository.save(rv);
                log.info("IN saveReview: user with id: {} successfully saved review on content with id: {}",user.getId(), content.getId());
                return review.getId();
            }
            else {
                throw new ContentNotFoundException("Content with id not found");
            }

        }
        else{
            throw new JwtAuthException("Find user error!");
        }

    }

    @Override
    public List<ReviewDto> getReviewByContent(Long contentId) {
        return reviewRepository.getReviewByContent(contentId)
                .stream().map(reviewMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getReviewByClient(Long userId) {
        return reviewRepository.getReviewByUser(userId)
                .stream().map(reviewMapper::toDTO).collect(Collectors.toList());
    }


}
