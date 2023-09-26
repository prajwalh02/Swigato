package com.example.Swigato.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartStatusResponse {

    String customerName;

    String address;

    String customerMobile;

    double cartTotal;

    List<FoodResponse> foodList;

    String restaurantName;
}
