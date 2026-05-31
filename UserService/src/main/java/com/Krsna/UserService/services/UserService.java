package com.Krsna.UserService.services;

import com.Krsna.UserService.entities.User;

import java.util.List;

public interface UserService {
    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user of given userId

    User getUser(String userId);

    void deleteUser(String userId);

    User updateUser(User user, String userId);

    User findByEmail(String email);

    List<User> findByNameContaining(String name);
}
