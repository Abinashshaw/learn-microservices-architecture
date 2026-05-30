package com.Krsna.UserService.external_services;

import com.Krsna.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings")
    public ResponseEntity<List<Rating>> getAllRatings();

    @PostMapping("/ratings")
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating);

    @GetMapping("/ratings/{ratingId}")
    public ResponseEntity<Rating> getRatingByRatingId(@PathVariable String ratingId);

    @DeleteMapping("/ratings/{ratingId}")
    public ResponseEntity<String> deleteRating(@PathVariable String ratingId);

    @PutMapping("/ratings/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, @RequestBody Rating rating);

    @GetMapping("/ratings/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId);

    @GetMapping("/ratings/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId);

}
