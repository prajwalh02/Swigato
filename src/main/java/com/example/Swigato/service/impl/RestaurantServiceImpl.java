package com.example.Swigato.service.impl;

import com.example.Swigato.dto.requestdto.FoodRequest;
import com.example.Swigato.dto.requestdto.RestaurantRequest;
import com.example.Swigato.dto.responsedto.RestaurantResponse;
import com.example.Swigato.exception.RestaurantNotFoundException;
import com.example.Swigato.model.FoodItem;
import com.example.Swigato.model.Restaurant;
import com.example.Swigato.repository.RestaurantRepository;
import com.example.Swigato.service.RestaurantService;
import com.example.Swigato.transformer.FoodTransformer;
import com.example.Swigato.transformer.RestaurantTransformer;
import com.example.Swigato.utils.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    final RestaurantRepository restaurantRepository;

    final ValidationUtils validationUtils;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ValidationUtils validationUtils) {
        this.restaurantRepository = restaurantRepository;
        this.validationUtils = validationUtils;
    }

    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {

        // dto -> model
        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);

        // save the model in db
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // prepare the response dto from model
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);

    }

    @Override
    public String changeOpenedStatus(int id) {

        // check whether id is valid or not
//        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
//
//        if(restaurantOptional.isEmpty()) {
//            throw new RestaurantNotFoundException("Restaurant does not exist !!!");
//        }

        if(!validationUtils.validateRestaurantId(id)){
            throw new RestaurantNotFoundException("Restaurant doesn't exist!!");
        }

        Restaurant restaurant = restaurantRepository.findById(id).get();
        restaurant.setOpened(!restaurant.isOpened());

        //save
        restaurantRepository.save(restaurant);

        if(restaurant.isOpened()) {
            return "Restaurant is Opened Now !!!";
        }
        return "Restaurant is currently closed";
    }

    @Override
    public RestaurantResponse addFoodItemToRestaurant(FoodRequest foodRequest) {

        // check restaurant is valid or not
        if(!validationUtils.validateRestaurantId(foodRequest.getRestaurantId())) {
            throw new RestaurantNotFoundException("Restaurant doesn't exist!!!");
        }

        Restaurant restaurant = restaurantRepository.findById(foodRequest.getRestaurantId()).get();

        //make food entity
        FoodItem foodItem = FoodTransformer.FoodRequestToFoodItem(foodRequest);
        foodItem.setRestaurant(restaurant);

        restaurant.getAvailableFoodItems().add(foodItem);

        // save restaurant and food
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // prepare response
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);

    }
}
