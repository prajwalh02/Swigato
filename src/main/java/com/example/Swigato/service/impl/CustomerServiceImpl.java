package com.example.Swigato.service.impl;

import com.example.Swigato.dto.requestdto.CustomerRequest;
import com.example.Swigato.dto.responsedto.CustomerResponse;
import com.example.Swigato.exception.CustomerNotFoundException;
import com.example.Swigato.model.Cart;
import com.example.Swigato.model.Customer;
import com.example.Swigato.repository.CustomerRepository;
import com.example.Swigato.service.CustomerService;
import com.example.Swigato.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        //dto --> customer model
        Customer customer = CustomerTransformer.CustomerRequestToCustomer(customerRequest);

        // allocate a cart
        Cart cart = Cart.builder()
                .cardTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        // save both customer and cart
        Customer savedCustomer = customerRepository.save(customer);    //save both customer & cart\

        //prepare the response dto
        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);

    }


    public CustomerResponse findCustomerByMobile(String mobile) {

        Customer customer = customerRepository.findByMobileNo(mobile);
        if(customer == null) {
            throw new CustomerNotFoundException("Invalid Mobile number !!!");
        }
        return CustomerTransformer.CustomerToCustomerResponse(customer);

    }
}
