package com.example.Swigato.controller;

import com.example.Swigato.dto.requestdto.DeliveryPartnerRequest;
import com.example.Swigato.service.impl.DeliveryPartnerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery-partner")
public class DeliveryPartnerController {

    final DeliveryPartnerServiceImpl deliveryPartnerService;

    public DeliveryPartnerController(DeliveryPartnerServiceImpl deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }


@PostMapping("/add")
    public ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest) {

        String message = deliveryPartnerService.addDeliveryPartner(deliveryPartnerRequest);
        return new ResponseEntity(message, HttpStatus.CREATED);
    }

    // get delivery partner with highest number of deliveries

    // send an email to all the partners who have done less than 10 deliveries.
}
