package com.example.Swigato.transformer;

import com.example.Swigato.dto.responsedto.CartStatusResponse;
import com.example.Swigato.dto.responsedto.FoodResponse;
import com.example.Swigato.model.Cart;
import com.example.Swigato.model.FoodItem;

public class CartStatusTransformer {


    public static CartStatusResponse CartToCartStatusResponse(Cart cart) {
        return CartStatusResponse.builder()
                .customerName(cart.getCustomer().getName())
                .customerMobile(cart.getCustomer().getMobileNo())
                .address(cart.getCustomer().getAddress())
                .build();
    }
}
