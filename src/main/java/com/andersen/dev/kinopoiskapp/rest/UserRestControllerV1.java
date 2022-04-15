package com.andersen.dev.kinopoiskapp.rest;

import com.andersen.dev.kinopoiskapp.dto.ReviewDto;
import com.andersen.dev.kinopoiskapp.rest.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.el.PropertyNotFoundException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "/api/v1/users/reviews/")
@RequiredArgsConstructor
public class UserRestControllerV1 {
    private final ReviewService reviewService;

    @PostMapping(value = "", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void postReview(@Valid @RequestBody ReviewDto reviewDto) {
        try {
            reviewService.saveReview(reviewDto);
        } catch (ConstraintViolationException | PropertyNotFoundException | RollbackException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("contents/{contentId}")
    public ResponseEntity<?> getReviewByContent(@PathVariable(name = "contentId") @Min(1L) Long contentId) {
        return new ResponseEntity<>(reviewService.getReviewByContent(contentId), HttpStatus.OK);
    }

}
