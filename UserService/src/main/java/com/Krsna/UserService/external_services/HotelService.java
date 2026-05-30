package com.Krsna.UserService.external_services;

import com.Krsna.UserService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getAllHotels();

    @PostMapping("/hotels")
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel);

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<Hotel> getHotelByHotelId(@PathVariable String hotelId);

    @DeleteMapping("/hotels/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable String hotelId);

    @PutMapping("/hotels/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable String hotelId);

    @GetMapping("/hotels/search/{name}")
    public ResponseEntity<List<Hotel>> findByNameContaining(@PathVariable String name);
}
