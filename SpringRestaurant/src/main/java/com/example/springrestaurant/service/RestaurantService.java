package com.example.springrestaurant.service;

import com.example.springrestaurant.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant saveRating(Restaurant r);
    List<Restaurant> getAllRestaurant();
    Restaurant updateRestaurant(Restaurant r,int id);
    void deleteRestaurant(int id);
    Restaurant getAvg(Restaurant r,int id);
}
