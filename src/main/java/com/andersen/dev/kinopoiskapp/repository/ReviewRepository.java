package com.andersen.dev.kinopoiskapp.repository;

import com.andersen.dev.kinopoiskapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query("from Review r where r.content.id = :id")
    List<Review> getReviewByContent(@Param(value = "id") long id);

    @Query("from Review r where r.user.id = :id")
    List<Review> getReviewByUser(@Param(value = "id") long id);

    @Query("from Review r where r.user.id = :clientId and r.content.id = :contentId")
    Review getReviewByUserAndContent(@Param(value = "clientId") long clientId, @Param(value = "contentId") long contentId);
}
