package com.example.Swigato.dto.responsedto;

import com.example.Swigato.enums.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodResponse {

    String dishName;

    double price;

    FoodCategory foodCategory;

    boolean veg;

}
