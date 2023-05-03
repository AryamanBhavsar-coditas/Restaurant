package com.example.springrestaurant.service.impl;

import com.example.springrestaurant.exception.ResourceNotFoundException;
import com.example.springrestaurant.model.Restaurant;
import com.example.springrestaurant.repository.RestaurantRepository;
import com.example.springrestaurant.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;


    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository=restaurantRepository;
    }


    @Override
    public Restaurant saveRating(Restaurant r) {
        return restaurantRepository.save(r);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant updateRestaurant(Restaurant r, int id) {

        Restaurant existingRes = restaurantRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Restaurant","id",id)
        );

        existingRes.setCleanliness(r.getCleanliness());
        existingRes.setAmbience(r.getAmbience());
        existingRes.setService(r.getService());
        existingRes.setFood(r.getFood());

        restaurantRepository.save(existingRes);

        return existingRes;

    }

    @Override
    public void deleteRestaurant(int id) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Restaurant","id",id)
        );

        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant getAvg(Restaurant r, int id) {
        Restaurant existingRes = restaurantRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Restaurant","id",id)
        );

        int sum = existingRes.getService()+existingRes.getCleanliness()+ existingRes.getAmbience()+existingRes.getFood();
        int avg = sum/4;

        existingRes.setAvg(avg);

        restaurantRepository.save(existingRes);

        return existingRes;
    }
}
