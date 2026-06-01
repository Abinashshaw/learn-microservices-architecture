package com.Krsna.UserService.external_services.fallbacks;

import com.Krsna.UserService.entities.Rating;
import com.Krsna.UserService.external_services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class RatingClientFallback implements RatingService {

    @Override
    public ResponseEntity<List<Rating>> getAllRatings() {
        // Safe empty list prevents NullPointerExceptions in your templates/streams
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Collections.emptyList());
    }

    @Override
    public ResponseEntity<Rating> saveRating(Rating rating) {
        // Returns a fallback object indicating save failure
        Rating fallbackRating = new Rating();
        fallbackRating.setFeedback("Fallback: Unable to save rating at this time.");
        fallbackRating.setRatings(0);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(fallbackRating);
    }

    @Override
    public ResponseEntity<Rating> getRatingByRatingId(String ratingId) {
        // Echoes the requested ratingId back with a fallback flag
        Rating fallbackRating = new Rating();
        fallbackRating.setRatingId(ratingId);
        fallbackRating.setFeedback("Fallback: Rating details temporarily unavailable.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(fallbackRating);
    }

    @Override
    public ResponseEntity<String> deleteRating(String ratingId) {
        // Simple, predictable failure message
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Delete failed: Rating service is down.");
    }

    @Override
    public ResponseEntity<Rating> updateRating(String ratingId, Rating rating) {
        // Marks the current payload as failed and sends it back
        if (rating != null) {
            rating.setRatingId(ratingId);
            rating.setFeedback(rating.getFeedback() + " (Update Failed)");
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(rating);
    }

    @Override
    public ResponseEntity<List<Rating>> getRatingsByUserId(String userId) {
        // Allows user profile pages to still load safely without user ratings
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Collections.emptyList());
    }

    @Override
    public ResponseEntity<List<Rating>> getRatingByHotelId(String hotelId) {
        // Allows hotel pages to still load safely without customer reviews
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Collections.emptyList());
    }
}
