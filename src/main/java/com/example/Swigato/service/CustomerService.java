package com.example.Swigato.service;

import com.example.Swigato.dto.requestdto.CustomerRequest;
import com.example.Swigato.dto.responsedto.CustomerResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustomerService {

    public CustomerResponse addCustomer(CustomerRequest customerRequest);

    public CustomerResponse findCustomerByMobile(String mobile);

}
