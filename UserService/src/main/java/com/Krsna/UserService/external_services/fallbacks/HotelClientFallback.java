package com.Krsna.UserService.external_services.fallbacks;

import com.Krsna.UserService.entities.Hotel;
import com.Krsna.UserService.external_services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class HotelClientFallback implements HotelService {

    @Override
    public ResponseEntity<List<Hotel>> getAllHotels() {
        // Returns an empty list to prevent NullPointerException in the UI/caller
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Collections.emptyList());
    }

    @Override
    public ResponseEntity<Hotel> saveHotel(Hotel hotel) {
        // Returns a dummy hotel object indicating the failure status
        Hotel fallbackHotel = new Hotel();
        fallbackHotel.setName("Fallback: Service Unavailable");
        fallbackHotel.setAbout("Could not save hotel at this time.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(fallbackHotel);
    }

    @Override
    public ResponseEntity<Hotel> getHotelByHotelId(String hotelId) {
        // Returns a dummy hotel populated with the requested ID
        Hotel fallbackHotel = new Hotel();
        fallbackHotel.setHotelId(hotelId);
        fallbackHotel.setName("Fallback Hotel");
        fallbackHotel.setAbout("Detailed information is temporarily unavailable.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(fallbackHotel);
    }

    @Override
    public ResponseEntity<String> deleteHotel(String hotelId) {
        // Returns a clear error message string
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Delete failed: Hotel service is currently down.");
    }

    @Override
    public ResponseEntity<Hotel> updateHotel(Hotel hotel, String hotelId) {
        // Returns the input data back so the application knows what failed
        if (hotel != null) {
            hotel.setName(hotel.getName() + " (Update Failed)");
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(hotel);
    }

    @Override
    public ResponseEntity<List<Hotel>> findByNameContaining(String name) {
        // Returns an empty list for search queries during downtime
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Collections.emptyList());
    }
}
