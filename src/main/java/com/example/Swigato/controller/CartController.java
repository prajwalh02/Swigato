package com.example.Swigato.controller;

import com.example.Swigato.dto.requestdto.FoodRequest;
import com.example.Swigato.dto.responsedto.CartStatusResponse;
import com.example.Swigato.service.impl.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity addFoodItemToCart(@RequestBody FoodRequest foodRequest) {
        try {
            CartStatusResponse cartStatusResponse = cartService.addFoodItemToCart(foodRequest);
            return new ResponseEntity(cartStatusResponse,HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
