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

    @GetMapping
    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Delivery getById(@PathVariable Long id) {
        return deliveryRepository.findById(id).orElseThrow(()->new RuntimeException("delivery not found"));
    }

    @PostMapping
    public Delivery create(@RequestBody Delivery delivery) {
        return  deliveryRepository.save(delivery);
    }



}
