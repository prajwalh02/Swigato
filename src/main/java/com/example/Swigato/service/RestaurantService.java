package com.example.Swigato.service;

import com.example.Swigato.dto.requestdto.FoodRequest;
import com.example.Swigato.dto.requestdto.RestaurantRequest;
import com.example.Swigato.dto.responsedto.RestaurantResponse;

public interface RestaurantService {

    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    public String changeOpenedStatus(int id);

    RestaurantResponse  addFoodItemToRestaurant(FoodRequest foodRequest);
}
