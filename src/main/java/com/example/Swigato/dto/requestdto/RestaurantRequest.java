package com.example.Swigato.dto.requestdto;

import com.example.Swigato.enums.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest {

    String name;

    String location;

    RestaurantCategory restaurantCategory;

    String contactNumber;
}
