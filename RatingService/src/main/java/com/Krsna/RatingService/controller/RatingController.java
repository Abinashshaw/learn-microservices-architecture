package com.Krsna.RatingService.controller;

import com.Krsna.RatingService.entities.Rating;
import com.Krsna.RatingService.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    Logger log = LoggerFactory.getLogger(RatingController.class.getName());

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @PostMapping
    public Rating saveRating(@RequestBody Rating rating) {
        return ratingService.saveRating(rating);
    }

    @GetMapping("/{ratingId}")
    public Rating getRatingByRatingId(@PathVariable String ratingId) {
        return ratingService.getRatingByRatingId(ratingId);
    }

    @DeleteMapping("/{ratingId}")
    public void deleteRating(@PathVariable String ratingId) {
        log.info("Deleting rating with id: {}", ratingId);
        ratingService.deleteRating(ratingId);
    }

    @PutMapping("/{ratingId}")
    public void updateRating(@PathVariable String ratingId, @RequestBody Rating rating) {
        ratingService.updateRating(rating, ratingId);
    }

    @GetMapping("/user/{userId}")
    public List<Rating> getRatingByUserId(@PathVariable String userId){
        return ratingService.getRatingByUserId(userId);
    }

    @GetMapping("/hotel/{hotelId}")
    public List<Rating> getRatingByHotelId(@PathVariable String hotelId){
        return ratingService.getRatingByHotelId(hotelId);
    }



}
