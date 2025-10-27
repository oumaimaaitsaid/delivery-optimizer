package com.delivery.optimizer.controller;

import com.delivery.optimizer.model.Vehicle;
import com.delivery.optimizer.repository.VehicleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Récupérer tous les véhicules
    @GetMapping
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    // Récupérer un véhicule par ID
    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    //  Créer un nouveau véhicule
    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }



}
