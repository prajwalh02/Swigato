package com.example.Swigato.service.impl;

import com.example.Swigato.dto.responsedto.OrderResponse;
import com.example.Swigato.exception.CustomerNotFoundException;
import com.example.Swigato.exception.EmptyCartException;
import com.example.Swigato.model.*;
import com.example.Swigato.repository.CustomerRepository;
import com.example.Swigato.repository.DeliveryPartnerRepository;
import com.example.Swigato.repository.OrderEntityRepository;
import com.example.Swigato.repository.RestaurantRepository;
import com.example.Swigato.service.OrderService;
import com.example.Swigato.transformer.OrderTransformer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {

    final CustomerRepository customerRepository;
    final DeliveryPartnerRepository deliveryPartnerRepository;
    final OrderEntityRepository orderEntityRepository;
    final RestaurantRepository restaurantRepository;

    public OrderServiceImpl(CustomerRepository customerRepository,
                            DeliveryPartnerRepository deliveryPartnerRepository,
                            OrderEntityRepository orderEntityRepository,
                            RestaurantRepository restaurantRepository) {
        this.customerRepository = customerRepository;
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.orderEntityRepository = orderEntityRepository;
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public OrderResponse placeOrder(String customerMobile) {

        // verify the customer
        Customer customer = customerRepository.findByMobileNo(customerMobile);
        if(customer == null) {
            throw new CustomerNotFoundException("Invalid mobile number!!!");
        }

        // verify if cart is empty or not
        Cart cart = customer.getCart();
        if(cart.getFoodItems().size() == 0) {
            throw new EmptyCartException("Sorry! Your cart is empty!!!");
        }

        // find a delivery partner to deliver. Randomly
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findRandomDeliveryPartner();
        Restaurant restaurant = cart.getFoodItems().get(0).getMenuItem().getRestaurant();

        // prepare the order entity
        OrderEntity order = OrderTransformer.prepareOrderEntity(cart);

        OrderEntity savedOrder = orderEntityRepository.save(order);

        order.setCustomer(customer);
        order.setDeliveryPartner(deliveryPartner);
        order.setRestaurant(restaurant);
        order.setFoodItems(cart.getFoodItems());

        customer.getOrders().add(savedOrder);
        deliveryPartner.getOrders().add(savedOrder);
        restaurant.getOrders().add(savedOrder);

        for(FoodItem foodItem: cart.getFoodItems()){
            foodItem.setCart(null);
            foodItem.setOrder(savedOrder);
        }

        clearCart(cart);

        // save
        customerRepository.save(customer);
        deliveryPartnerRepository.save(deliveryPartner);
        restaurantRepository.save(restaurant);

        // prepare order response
        return OrderTransformer.OrderToOrderResponse(savedOrder);
    }

    private void clearCart(Cart cart) {
        cart.setCardTotal(0);
        cart.setFoodItems(new ArrayList<>());
    }
}
