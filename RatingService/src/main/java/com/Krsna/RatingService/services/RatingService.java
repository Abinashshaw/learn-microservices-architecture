package com.Krsna.RatingService.services;

import com.Krsna.RatingService.entities.Rating;
import com.Krsna.RatingService.repository.RatingRepository;

import java.util.List;

public interface RatingService {

    public List<Rating> getAllRatings();
    public Rating saveRating(Rating rating);
    public Rating getRatingByRatingId(String ratingId);
    public void deleteRating(String ratingId);
    public Rating updateRating(Rating rating, String ratingId);
    public List<Rating> getRatingsByUserId(String userId);
    public List<Rating> getRatingByHotelId(String hotelId);

}
