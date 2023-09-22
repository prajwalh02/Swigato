package com.example.Swigato.transformer;

import com.example.Swigato.dto.requestdto.RestaurantRequest;
import com.example.Swigato.dto.responsedto.FoodResponse;
import com.example.Swigato.dto.responsedto.RestaurantResponse;
import com.example.Swigato.model.FoodItem;
import com.example.Swigato.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTransformer {

    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest restaurantRequest) {
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .contactNumber(restaurantRequest.getContactNumber())
                .location(restaurantRequest.getLocation())
                .opened(true)
                .availableFoodItems(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
    }

    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant) {

        List<FoodResponse> menu = restaurant.getAvailableFoodItems()
                .stream()
                .map(foodItem -> FoodTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return RestaurantResponse.builder()
                .name(restaurant.getName())
                .contactNumber(restaurant.getContactNumber())
                .location(restaurant.getLocation())
                .opened(restaurant.isOpened())
                .menu(menu)
                .build();
    }
}