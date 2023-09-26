package com.example.Swigato.service;

import com.example.Swigato.dto.requestdto.FoodRequest;
import com.example.Swigato.dto.responsedto.CartStatusResponse;

public interface CartService {
    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest);
}
