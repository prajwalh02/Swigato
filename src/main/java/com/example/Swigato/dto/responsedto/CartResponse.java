package com.example.Swigato.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    int cardTotal;

    List<FoodResponse> foodItems;
}
