package com.Krsna.HotelService.services;

import com.Krsna.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> getAllHotels();

    Hotel saveHotel(Hotel hotel);

    Hotel getHotelByHotelId(String hotelId);

    void deleteHotel(String hotelId);

    Hotel updateHotel(Hotel hotel, String hotelId);

    List<Hotel> findByNameContaining(String name);
}
