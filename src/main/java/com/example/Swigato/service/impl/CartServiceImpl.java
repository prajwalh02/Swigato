package com.example.Swigato.service.impl;

import com.example.Swigato.dto.requestdto.FoodRequest;
import com.example.Swigato.dto.responsedto.CartStatusResponse;
import com.example.Swigato.dto.responsedto.FoodResponse;
import com.example.Swigato.exception.CustomerNotFoundException;
import com.example.Swigato.exception.MenuItemNotFoundException;
import com.example.Swigato.exception.RestaurantNotFoundException;
import com.example.Swigato.model.Cart;
import com.example.Swigato.model.Customer;
import com.example.Swigato.model.FoodItem;
import com.example.Swigato.model.MenuItem;
import com.example.Swigato.repository.CartRepository;
import com.example.Swigato.repository.CustomerRepository;
import com.example.Swigato.repository.FoodRepository;
import com.example.Swigato.repository.MenuRepository;
import com.example.Swigato.service.CartService;
import com.example.Swigato.transformer.CartStatusTransformer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    final CustomerRepository customerRepository;

    final MenuRepository menuRepository;

    final CartRepository cartRepository;

    final FoodRepository foodRepository;

    public CartServiceImpl(CustomerRepository customerRepository,
                           MenuRepository menuRepository,
                           CartRepository cartRepository,
                           FoodRepository foodRepository) {
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
        this.cartRepository = cartRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest) {

        //check customer id valid or not
        Customer customer = customerRepository.findByMobileNo(foodRequest.getCustomerMobile());
        if(customer == null) {
            throw new CustomerNotFoundException("Customer does not exists !!!");
        }

        //check menu is valid or not
        Optional<MenuItem> menuItemOptional = menuRepository.findById(foodRequest.getMenuItemId());
        if(menuItemOptional.isEmpty()) {
            throw new MenuItemNotFoundException("Item is not available in the restaurant !!!");
        }

        //check restaurant is opened or not
        MenuItem menuItem = menuItemOptional.get();
        if(!menuItem.getRestaurant().isOpened()) {
            throw new RestaurantNotFoundException("Restaurant is Currently Closed!!!");
        }

        //check menu item is available or not
        if(!menuItem.isAvailable()) {
            throw new MenuItemNotFoundException("Given dish is not available at this moment!!!");
        }

        //ready to add item in the cart
        //convert dto to model
        //foodRequest --> foodItem
        FoodItem foodItem = FoodItem.builder()
                .menuItem(menuItem)
                .cart(customer.getCart())
                .requiredQuantity(foodRequest.getRequiredQuantity())
                .totalCost(foodRequest.getRequiredQuantity() * menuItem.getPrice())
                .build();

        Cart cart = customer.getCart();
        FoodItem savedFoodItem = foodRepository.save(foodItem);

        double cartTotal = 0;
        for(FoodItem food : cart.getFoodItems()) {
            cartTotal += food.getRequiredQuantity() * food.getMenuItem().getPrice();
        }
        cart.getFoodItems().add(savedFoodItem);
        cart.setCardTotal(cartTotal);
        menuItem.getFoodItems().add(savedFoodItem);

        //save
        Cart savedCart = cartRepository.save(cart);
        MenuItem savedMenuItem = menuRepository.save(menuItem);

        //prepare response dto
        List<FoodResponse> foodResponseList = new ArrayList<>();
        for(FoodItem food : cart.getFoodItems()) {
            FoodResponse foodResponse = CartStatusTransformer.FoodItemToFoodResponse(food);
            foodResponseList.add(foodResponse);
        }

        CartStatusResponse cartStatusResponse = CartStatusTransformer.CartToCartStatusResponse(savedCart);
        cartStatusResponse.setFoodList(foodResponseList);
        cartStatusResponse.setCartTotal(cartTotal);
        cartStatusResponse.setRestaurantName(savedMenuItem.getRestaurant().getName());

        return cartStatusResponse;

    }
}
