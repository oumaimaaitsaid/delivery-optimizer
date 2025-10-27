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

    // update un véhicule
    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id, @RequestBody Vehicle updatedVehicle) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setType(updatedVehicle.getType());
        vehicle.setMaxWeight(updatedVehicle.getMaxWeight());
        vehicle.setMaxVolume(updatedVehicle.getMaxVolume());
        vehicle.setMaxDeliveries(updatedVehicle.getMaxDeliveries());

        return vehicleRepository.save(vehicle);
    }

    //Supprimer un véhicule
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehicleRepository.deleteById(id);
    }
}
