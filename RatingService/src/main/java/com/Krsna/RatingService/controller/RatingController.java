package com.Krsna.RatingService.controller;

import com.Krsna.RatingService.entities.Rating;
import com.Krsna.RatingService.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    Logger log = LoggerFactory.getLogger(RatingController.class.getName());

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @PostMapping
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) {
        return new ResponseEntity<>(ratingService.saveRating(rating), HttpStatus.CREATED);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRatingByRatingId(@PathVariable String ratingId) {
        return ResponseEntity.ok(ratingService.getRatingByRatingId(ratingId));
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<String> deleteRating(@PathVariable String ratingId) {
        log.info("Deleting rating with id: {}", ratingId);
        ratingService.deleteRating(ratingId);
        return ResponseEntity.ok("Rating deleted with id "+ratingId);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, @RequestBody Rating rating) {
        return new ResponseEntity(ratingService.updateRating(rating, ratingId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
        return  ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }



}
