package com.example.Swigato.controller;

import com.example.Swigato.dto.requestdto.CustomerRequest;
import com.example.Swigato.dto.responsedto.CustomerResponse;
import com.example.Swigato.service.CustomerService;
import com.example.Swigato.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {


    // use in industry ----> called as Constructor injection, used while writing professional code
//    final CustomerServiceImpl customerService;
//    @Autowired
//    public CustomerController(CustomerServiceImpl customerService) {
//        this.customerService = customerService;
//    }


//    Field injection / attribute injection
    @Autowired
    CustomerServiceImpl customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest) {

        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);

        return new ResponseEntity(customerResponse, HttpStatus.CREATED);

    }

    @GetMapping("/find/mobile/{mobile}")
    public ResponseEntity getCustomerByMobile(@PathVariable("mobile") String mobile) {
        try{
            CustomerResponse customerResponse = customerService.findCustomerByMobile(mobile);
            return new ResponseEntity(customerResponse, HttpStatus.FOUND);
        }
        catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
