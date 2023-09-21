package com.example.Swigato.transformer;


import com.example.Swigato.dto.requestdto.CustomerRequest;
import com.example.Swigato.dto.responsedto.CartResponse;
import com.example.Swigato.dto.responsedto.CustomerResponse;
import com.example.Swigato.model.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest) {

        return Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .address(customerRequest.getAddress())
                .mobileNo(customerRequest.getMobileNo())
                .gender(customerRequest.getGender())
                .build();
    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer) {

        CartResponse cartResponse = CartTransformer.CartToCartResponse(customer.getCart());

        return CustomerResponse.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .mobileNo(customer.getMobileNo())
                .cart(cartResponse)
                .build();
    }
}
