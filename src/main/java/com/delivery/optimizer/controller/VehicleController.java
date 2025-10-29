package com.delivery.optimizer.controller;

import com.delivery.optimizer.dto.VehicleDTO;
import com.delivery.optimizer.mapper.VehicleMapper;
import com.delivery.optimizer.model.Vehicle;
import com.delivery.optimizer.model.VehicleType;
import com.delivery.optimizer.repository.VehicleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Récupérer tous les véhicules
    @GetMapping
    public List<VehicleDTO> getAll() {
        return vehicleRepository.findAll()
                .stream()
                .map(VehicleMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer un véhicule par ID
    @GetMapping("/{id}")
    public VehicleDTO getById(@PathVariable Long id) {
        Vehicle vehicle= vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return VehicleMapper.toDTO(vehicle);
    }

    //  Créer un nouveau véhicule
    @PostMapping
    public VehicleDTO create(@RequestBody VehicleDTO dto) {
        Vehicle vehicle=VehicleMapper.toEntity(dto);
        Vehicle saved= vehicleRepository.save(vehicle);
        return VehicleMapper.toDTO(saved);
    }

    // update un véhicule
    @PutMapping("/{id}")
    public VehicleDTO update(@PathVariable Long id, @RequestBody VehicleDTO dto ) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setType(VehicleType.valueOf(dto.getType().toUpperCase()));
        vehicle.setMaxWeight(dto.getMaxWeight());
        vehicle.setMaxVolume(dto.getMaxVolume());
        vehicle.setMaxDeliveries(dto.getMaxDeliveries());

        Vehicle updated = vehicleRepository.save(vehicle);
        return VehicleMapper.toDTO(updated);
    }

    //Supprimer un véhicule
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehicleRepository.deleteById(id);
    }
}
