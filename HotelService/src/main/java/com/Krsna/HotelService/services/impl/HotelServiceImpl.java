package com.Krsna.HotelService.services.impl;

import com.Krsna.HotelService.repository.HotelRepository;
import com.Krsna.HotelService.services.HotelService;
import com.Krsna.HotelService.entities.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        hotel.setHotelId(UUID.randomUUID().toString());
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelByHotelId(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found with Given hotelId "+hotelId));
    }

    @Override
    public void deleteHotel(String hotelId) {
        getHotelByHotelId(hotelId);
        hotelRepository.deleteById(hotelId);
    }

    @Override
    public Hotel updateHotel(Hotel hotel, String hotelId) {
        Hotel savedHotel = getHotelByHotelId(hotelId);
        if(hotel.getAbout() != null) savedHotel.setAbout(hotel.getAbout());
        if(hotel.getName() != null) savedHotel.setName(hotel.getName());
        if(hotel.getLocation() != null) savedHotel.setLocation(hotel.getLocation());
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> findByNameContaining(String name) {
        return hotelRepository.findByNameContaining(name);
    }
}
