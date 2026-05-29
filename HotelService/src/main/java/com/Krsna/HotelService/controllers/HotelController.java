package com.Krsna.HotelService.controllers;

import com.Krsna.HotelService.entities.Hotel;
import com.Krsna.HotelService.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PostMapping
    public Hotel saveHotel(@RequestBody Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }

    @GetMapping("/{hotelId}")
    public Hotel getHotelByHotelId(@PathVariable String hotelId) {
        return hotelService.getHotelByHotelId(hotelId);
    }

    @DeleteMapping("/{hotelId}")
    public void deleteHotel(@PathVariable String hotelId) {
        hotelService.deleteHotel(hotelId);
    }

    @PutMapping("/{hotelId}")
    public void updateHotel(@RequestBody Hotel hotel, @PathVariable String hotelId) {
        hotelService.updateHotel(hotel, hotelId);
    }

    @GetMapping("/search/{name}")
    public List<Hotel> findByNameContaining(@PathVariable String name) {
        return hotelService.findByNameContaining(name);
    }
}
