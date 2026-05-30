package com.Krsna.HotelService.controllers;

import com.Krsna.HotelService.entities.Hotel;
import com.Krsna.HotelService.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.saveHotel(hotel), HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(hotelService.getHotelByHotelId(hotelId));
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable String hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok("Hotel deleted with id "+hotelId);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable String hotelId) {
        return new ResponseEntity(hotelService.updateHotel(hotel, hotelId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Hotel>> findByNameContaining(@PathVariable String name) {
        return ResponseEntity.ok(hotelService.findByNameContaining(name));
    }
}
