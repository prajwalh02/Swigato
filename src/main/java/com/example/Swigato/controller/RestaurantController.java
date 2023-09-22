package com.example.Swigato.controller;

import com.example.Swigato.dto.requestdto.FoodRequest;
import com.example.Swigato.dto.requestdto.RestaurantRequest;
import com.example.Swigato.dto.responsedto.FoodResponse;
import com.example.Swigato.dto.responsedto.RestaurantResponse;
import com.example.Swigato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    final RestaurantService restaurantService;

    /**
     * Constructor Injection
     * @param restaurantService --> bean of restaurant Service
     */

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        RestaurantResponse restaurantResponse = restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/status")
    public ResponseEntity changeOpenedStatus(@RequestParam int id){
        String message = restaurantService.changeOpenedStatus(id);

        //if i don't want to show the exception to the client then dont catch the exception simply return
        return new ResponseEntity(message, HttpStatus.ACCEPTED);
    }

    @PostMapping("add/food")
    public ResponseEntity addFoodItemToRestaurant(@RequestBody FoodRequest foodRequest) {
        RestaurantResponse restaurantResponse = restaurantService.addFoodItemToRestaurant(foodRequest);
        return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
    }

    // get menu of a restaurant
}
