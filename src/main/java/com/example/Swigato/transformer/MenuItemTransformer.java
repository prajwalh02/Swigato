package com.example.Swigato.transformer;

import com.example.Swigato.dto.requestdto.MenuRequest;
import com.example.Swigato.dto.responsedto.MenuResponse;
import com.example.Swigato.model.MenuItem;

public class MenuItemTransformer {

    public static MenuItem MenuRequestToMenuItem(MenuRequest menuRequest) {
        return MenuItem.builder()
                .dishName(menuRequest.getDishName())
                .price(menuRequest.getPrice())
                .foodCategory(menuRequest.getCategory())
                .veg(menuRequest.isVeg())
                .available(menuRequest.isAvailable())
                .build();
    }

    public static MenuResponse MenuItemToMenuResponse(MenuItem menuItem) {
        return MenuResponse.builder()
                .dishName(menuItem.getDishName())
                .foodCategory(menuItem.getFoodCategory())
                .price(menuItem.getPrice())
                .veg(menuItem.isVeg())
                .build();
    }

}
