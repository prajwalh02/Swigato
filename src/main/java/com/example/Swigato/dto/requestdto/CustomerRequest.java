package com.example.Swigato.dto.requestdto;

import com.example.Swigato.enums.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    String name;

    String email;

    String address;

    String mobileNo;

    Gender gender;

}
