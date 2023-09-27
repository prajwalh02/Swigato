package com.example.Swigato.transformer;

import com.example.Swigato.dto.responsedto.FoodResponse;
import com.example.Swigato.dto.responsedto.OrderResponse;
import com.example.Swigato.model.Cart;
import com.example.Swigato.model.FoodItem;
import com.example.Swigato.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {

    public static OrderEntity prepareOrderEntity(Cart cart) {
        return OrderEntity.builder()
                .orderId(String.valueOf(UUID.randomUUID()))
                .orderTotal(cart.getCardTotal())
                .build();
    }

    public static OrderResponse OrderToOrderResponse(OrderEntity savedOrder) {

        List<FoodResponse> foodResponseList = new ArrayList<>();
        for(FoodItem food: savedOrder.getFoodItems()) {
            FoodResponse foodResponse = FoodResponse.builder()
                    .dishName(food.getMenuItem().getDishName())
                    .foodCategory(food.getMenuItem().getFoodCategory())
                    .price(food.getMenuItem().getPrice())
                    .veg(food.getMenuItem().isVeg())
                    .quantityAdded(food.getRequiredQuantity())
                    .build();

            foodResponseList.add(foodResponse);
        }

        return  OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .orderTime(savedOrder.getOrderTime())
                .orderTotal(savedOrder.getOrderTotal())
                .customerName(savedOrder.getCustomer().getName())
                .customerMobile(savedOrder.getCustomer().getMobileNo())
                .restaurantName(savedOrder.getRestaurant().getName())
                .deliveryPartnerName(savedOrder.getDeliveryPartner().getName())
                .deliveryPartnerMobile(savedOrder.getDeliveryPartner().getMobileNo())
                .foodResponses(foodResponseList)
                .build();

    }
}
