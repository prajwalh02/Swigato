package com.example.Swigato.transformer;

import com.example.Swigato.dto.responsedto.FoodResponse;
import com.example.Swigato.model.FoodItem;

public class FoodTransformer {

    public static FoodResponse FoodToFoodResponse(FoodItem food){
        return FoodResponse.builder()
                .dishName(food.getMenuItem().getDishName())
                .price(food.getMenuItem().getPrice())
                .foodCategory(food.getMenuItem().getFoodCategory())
                .veg(food.getMenuItem().isVeg())
                .quantityAdded(food.getRequiredQuantity())
                .build();
    }
}
