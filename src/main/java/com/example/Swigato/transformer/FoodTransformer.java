package com.example.Swigato.transformer;

import com.example.Swigato.dto.requestdto.FoodRequest;
import com.example.Swigato.dto.responsedto.FoodResponse;
import com.example.Swigato.model.FoodItem;

public class FoodTransformer {

    public static FoodItem FoodRequestToFoodItem(FoodRequest foodRequest) {
        return FoodItem.builder()
                .dishName(foodRequest.getDishName())
                .price(foodRequest.getPrice())
                .foodCategory(foodRequest.getCategory())
                .veg(foodRequest.isVeg())
                .available(foodRequest.isAvailable())
                .build();
    }

    public static FoodResponse FoodItemToFoodResponse(FoodItem foodItem) {
        return FoodResponse.builder()
                .dishName(foodItem.getDishName())
                .foodCategory(foodItem.getFoodCategory())
                .price(foodItem.getPrice())
                .veg(foodItem.isVeg())
                .build();
    }

}
