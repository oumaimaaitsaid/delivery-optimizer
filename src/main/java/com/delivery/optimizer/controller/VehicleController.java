package com.delivery.optimizer.controller;

import com.delivery.optimizer.dto.VehicleDTO;
import com.delivery.optimizer.mapper.VehicleMapper;
import com.delivery.optimizer.model.Vehicle;
import com.delivery.optimizer.model.VehicleType;
import com.delivery.optimizer.repository.VehicleRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle with id:"+id+" not found"));
        return VehicleMapper.toDTO(vehicle);
    }

    //  Créer un nouveau véhicule
    @PostMapping
    public VehicleDTO create(@RequestBody VehicleDTO dto) {
        validate(dto);
        Vehicle vehicle=VehicleMapper.toEntity(dto);
        Vehicle saved= vehicleRepository.save(vehicle);
        return VehicleMapper.toDTO(saved);
    }

    // update un véhicule
    @PutMapping("/{id}")
    public VehicleDTO update(@PathVariable Long id, @RequestBody VehicleDTO dto ) {
        validate(dto);
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle with id:"+id+" not found"));

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
        if (!vehicleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle with id:"+id+" not found");
        }
        vehicleRepository.deleteById(id);
    }

    private void validate(VehicleDTO dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payload manquant");
        }
        if (dto.getType() == null || dto.getType().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le type est obligatoire (BIKE, VAN, TRUCK)");
        }
        String t = dto.getType().toUpperCase();
        try {
            VehicleType.valueOf(t);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Type invalide. Valeurs acceptées: BIKE, VAN, TRUCK");
        }
        if (dto.getMaxWeight() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "maxWeight doit être > 0");
        }
        if (dto.getMaxVolume() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "maxVolume doit être > 0");
        }
        if (dto.getMaxDeliveries() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "maxDeliveries doit être > 0");
        }
    }
}
