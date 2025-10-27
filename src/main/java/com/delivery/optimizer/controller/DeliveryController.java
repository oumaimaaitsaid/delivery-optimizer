package com.delivery.optimizer.controller;

import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.repository.DeliveryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
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

    @PutMapping("/{id}")
    public  Delivery update(@PathVariable Long id, @RequestBody Delivery updated) {
        Delivery d = deliveryRepository.findById(id).orElseThrow(()-> new RuntimeException("delivery not found"));
        d.setLatitude(updated.getLatitude());
        d.setLongitude(updated.getLongitude());
        d.setWeight(updated.getWeight());
        d.setVolume(updated.getVolume());
        d.setStatus(updated.getStatus());
        return  deliveryRepository.save(d);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deliveryRepository.deleteById(id);
    }
}
