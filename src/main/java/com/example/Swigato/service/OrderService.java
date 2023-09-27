package com.example.Swigato.service;

import com.example.Swigato.dto.responsedto.OrderResponse;

public interface OrderService {
    public OrderResponse placeOrder(String customerMobile);
}
