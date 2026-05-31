package com.Krsna.HotelService.repository;

import com.Krsna.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, String> {

    List<Hotel> findByNameContaining(String name);
}
