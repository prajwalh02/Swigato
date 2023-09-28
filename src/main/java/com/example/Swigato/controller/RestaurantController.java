package com.example.Swigato.controller;

import com.example.Swigato.dto.requestdto.MenuRequest;
import com.example.Swigato.dto.requestdto.RestaurantRequest;
import com.example.Swigato.dto.responsedto.RestaurantResponse;
import com.example.Swigato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("add/menu")
    public ResponseEntity addMenuItemToRestaurant(@RequestBody MenuRequest menuRequest) {
        RestaurantResponse restaurantResponse = restaurantService.addMenuItemToRestaurant(menuRequest);
        return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
    }

    // get menu of a restaurant

    // give all the restaurants who have served more than 'x' number of orders

    // give the restaurants which have maximum number of items in their menu and which are opened also
}
