package com.delivery.optimizer.controller;

import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.repository.DeliveryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DeliveryController {
    private final DeliveryRepository deliveryRepository;

    public DeliveryController(DeliveryRepository deliveryRepository)
    {
        this.deliveryRepository = deliveryRepository;
    }








}
