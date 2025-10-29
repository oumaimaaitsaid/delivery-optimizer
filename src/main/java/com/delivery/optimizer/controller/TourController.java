package com.delivery.optimizer.controller;

import com.delivery.optimizer.dto.TourDTO;
import com.delivery.optimizer.mapper.TourMapper;
import com.delivery.optimizer.model.Tour;
import com.delivery.optimizer.repository.DeliveryRepository;
import com.delivery.optimizer.repository.TourRepository;
import com.delivery.optimizer.repository.VehicleRepository;
import com.delivery.optimizer.repository.WarehouseRepository;
import com.delivery.optimizer.service.TourService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/tours")
public class TourController {

    private final TourRepository tourRepository;
    private final VehicleRepository vehicleRepository;
    private final WarehouseRepository warehouseRepository;

    public TourController(TourRepository tourRepository,
                          VehicleRepository vehicleRepository,
                          WarehouseRepository warehouseRepository,
                          DeliveryRepository deliveryRepository,
                          TourService tourService) {
        this.tourRepository = tourRepository;
        this.vehicleRepository = vehicleRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @GetMapping
    public List<TourDTO> getTours() {
        return tourRepository.findAll()
                .stream()
                .map(TourMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TourDTO getTourById(@PathVariable Long id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found"));
        return TourMapper.toDTO(tour);
    }

    @PostMapping
    public TourDTO createTour(@RequestBody TourDTO dto) {
        Tour tour = TourMapper.toEntity(dto);
        if (dto.getVehicleId() != null) {
            tour.setVehicle(vehicleRepository.findById(dto.getVehicleId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found")));
        }
        if (dto.getWarehouseId() != null) {
            tour.setWarehouse(warehouseRepository.findById(dto.getWarehouseId())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found")));
        }
        Tour saved = tourRepository.save(tour);
        return TourMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public TourDTO update(@PathVariable Long id, @RequestBody TourDTO dto) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found"));
        tour.setDate(dto.getDate());
        if (dto.getDeliveryIds() != null) {
            tour.setVehicle(vehicleRepository.findById(dto.getVehicleId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found")));
        }
        if (dto.getWarehouseId() != null) {
            tour.setWarehouse(warehouseRepository.findById(dto.getWarehouseId())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found")));
        }
        Tour updated = tourRepository.save(tour);
        return TourMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tourRepository.deleteById(id);
    }

  }
