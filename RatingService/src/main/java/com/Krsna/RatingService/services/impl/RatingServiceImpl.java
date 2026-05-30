package com.Krsna.RatingService.services.impl;

import com.Krsna.RatingService.entities.Rating;
import com.Krsna.RatingService.exceptions.ResourceNotFoundException;
import com.Krsna.RatingService.repository.RatingRepository;
import com.Krsna.RatingService.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    Logger log = LoggerFactory.getLogger(RatingServiceImpl.class.getName());

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating saveRating(Rating rating) {
        rating.setRatingId(UUID.randomUUID().toString());
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRatingByRatingId(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found by Rating Id "+ratingId));
    }

    @Override
    public void deleteRating(String ratingId) {
        Rating rating = getRatingByRatingId(ratingId);
        log.info("Rating found {}", rating);
        ratingRepository.deleteById(ratingId);
    }

    @Override
    public Rating updateRating(Rating rating, String ratingId) {
        Rating savedRating = getRatingByRatingId(ratingId);
        if(rating.getRatings() != null) savedRating.setRatings(rating.getRatings());
        if(rating.getFeedback() != null) savedRating.setFeedback(rating.getFeedback());
        return ratingRepository.save(savedRating);
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
