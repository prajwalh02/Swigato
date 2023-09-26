package com.example.Swigato.transformer;

import com.example.Swigato.dto.responsedto.CartResponse;
import com.example.Swigato.model.Cart;

import java.util.ArrayList;

public class CartTransformer {
    public static CartResponse CartToCartResponse(Cart cart) {
        return CartResponse.builder()
                .cartTotal(cart.getCardTotal())
                .foodItems(new ArrayList<>())
                .build();
    }
}
