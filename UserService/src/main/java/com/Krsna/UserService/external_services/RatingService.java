package com.Krsna.UserService.external_services;

import com.Krsna.UserService.entities.Rating;
import com.Krsna.UserService.external_services.fallbacks.RatingClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RATING-SERVICE", fallback = RatingClientFallback.class)
public interface RatingService {

    @GetMapping("/api/v1/ratings")
    public ResponseEntity<List<Rating>> getAllRatings();

    @PostMapping("/api/v1/ratings")
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating);

    @GetMapping("/api/v1/ratings/{ratingId}")
    public ResponseEntity<Rating> getRatingByRatingId(@PathVariable String ratingId);

    @DeleteMapping("/api/v1/ratings/{ratingId}")
    public ResponseEntity<String> deleteRating(@PathVariable String ratingId);

    @PutMapping("/api/v1/ratings/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, @RequestBody Rating rating);

    @GetMapping("/api/v1/ratings/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId);

    @GetMapping("/api/v1/ratings/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId);

}
