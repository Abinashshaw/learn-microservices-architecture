package com.Krsna.UserService.services.impl;

import com.Krsna.UserService.entities.Hotel;
import com.Krsna.UserService.entities.Rating;
import com.Krsna.UserService.entities.User;
import com.Krsna.UserService.exceptions.ResourceNotFoundException;
import com.Krsna.UserService.repository.UserRepository;
import com.Krsna.UserService.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
//        //get ratings by user id
        users.stream().map(user -> { user.setRatings(
                            getRatingsByUserId(user.getUserId()).stream().map(rating -> {
                                rating.setHotel(getHotelByHotelId(rating.getHotelId())); return rating;
                            }).toList()
                        ); return user;}).toList();


        log.info("Users with Ratings fetched successfully {}", users);

        return users;
    }


    public List<Rating> getRatingsByUserId(String userId) {
//        ArrayList<Rating> ratings = restTemplate.getForObject("http://localhost:9092/ratings/user/"+userId, ArrayList.class);
//        ratings = objectMapper.convertValue(ratings, new TypeReference<ArrayList<Rating>>(){});
//        restTemplate.exchange("http://localhost:9092/ratings/user/"+userId, HttpMethod.GET, null, new ParameterizedTypeReference<Rating>(){});
//        Rating[] ratings = restTemplate.getForObject("http://localhost:9092/ratings/user/"+userId, Rating[].class);

//        ratings.stream().map(rating -> {
//            rating.setHotel(getHotelByHotelId(rating.getHotelId())); return rating;
//        }).toList();
//        log.info("Ratings found with user id: {} is {}", userId, ratings);

//        Rating[] ratings = restTemplate.getForObject("http://localhost:9092/ratings/user/" + userId, Rating[].class);

//        ResponseEntity<ArrayList> response = restTemplate.getForEntity("http://localhost:9092/ratings/user/" + userId, ArrayList.class);
        ResponseEntity<ArrayList> response = restTemplate.getForEntity("http://RATING-SERVICE/ratings/user/" + userId, ArrayList.class);
        ArrayList<Rating> ratings = new ArrayList<>();
        if(response.getStatusCode().is2xxSuccessful()){
            log.info("Ratings found with user id: {} is {}", userId, response);
            ratings = objectMapper.convertValue(response.getBody(), new TypeReference<ArrayList<Rating>>(){});
        }

        return ratings;
    }

    public Hotel getHotelByHotelId(String hotelId) {
//        ResponseEntity<Hotel> response = restTemplate.getForEntity("http://localhost:9093/hotels/"+hotelId, Hotel.class);
        ResponseEntity<Hotel> response = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+hotelId, Hotel.class);
        if (response.getStatusCode().is2xxSuccessful()){
            log.info("Hotel found with id: {} is {}", hotelId, response);
        }
        return response.getBody();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with Given Id "+userId));
        List<Rating> ratings = getRatingsByUserId(userId);
        user.setRatings(ratings);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        getUser(userId);
        userRepository.delete(getUser(userId));
    }

    @Override
    public User updateUser(User user, String userId) {
        User savedUser = getUser(userId);
        if(user.getName() != null) {
            savedUser.setName(user.getName());
        }
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with Given Email"+email));
    }

    @Override
    public List<User> findByNameContaining(String name) {
        return userRepository.findByNameContaining(name);
    }
}
