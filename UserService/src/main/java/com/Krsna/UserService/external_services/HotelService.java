package com.Krsna.UserService.external_services;

import com.Krsna.UserService.entities.Hotel;
import com.Krsna.UserService.external_services.fallbacks.HotelClientFallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "HOTEL-SERVICE", fallback = HotelClientFallback.class)
public interface HotelService {

    @GetMapping("/api/v1/hotels")
    public ResponseEntity<List<Hotel>> getAllHotels();

    @PostMapping("/api/v1/hotels")
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel);

    @GetMapping("/api/v1/hotels/{hotelId}")
    public ResponseEntity<Hotel> getHotelByHotelId(@PathVariable String hotelId);

    @DeleteMapping("/api/v1/hotels/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable String hotelId);

    @PutMapping("/api/v1/hotels/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable String hotelId);

    @GetMapping("/api/v1/hotels/search/{name}")
    public ResponseEntity<List<Hotel>> findByNameContaining(@PathVariable String name);
}
