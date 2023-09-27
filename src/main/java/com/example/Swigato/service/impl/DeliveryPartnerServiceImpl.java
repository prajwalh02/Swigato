package com.example.Swigato.service.impl;

import com.example.Swigato.dto.requestdto.DeliveryPartnerRequest;
import com.example.Swigato.model.DeliveryPartner;
import com.example.Swigato.repository.DeliveryPartnerRepository;
import com.example.Swigato.service.DeliveryPartnerService;
import com.example.Swigato.transformer.DeliveryPartnerTransformer;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

    final DeliveryPartnerRepository deliveryPartnerRepository;

    public DeliveryPartnerServiceImpl(DeliveryPartnerRepository deliveryPartnerRepository) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
    }

    @Override
    public String addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest) {


        // dto --> model
        DeliveryPartner deliveryPartner = DeliveryPartnerTransformer.DeliveryPartnerRequestToDeliveryPartner(deliveryPartnerRequest);

        // save
        DeliveryPartner savedPartner = deliveryPartnerRepository.save(deliveryPartner);

        return "You have been successfully registered!!!";
    }
}
