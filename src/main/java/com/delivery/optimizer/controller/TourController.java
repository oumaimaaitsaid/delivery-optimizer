package com.delivery.optimizer.controller;

import com.delivery.optimizer.dto.TourDTO;
import com.delivery.optimizer.mapper.TourMapper;
import com.delivery.optimizer.model.Tour;
import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.model.Vehicle;
import com.delivery.optimizer.model.Warehouse;
import com.delivery.optimizer.optimizer.ClarkeWrightOptimizer;
import com.delivery.optimizer.optimizer.TourOptimizer;
import com.delivery.optimizer.repository.DeliveryRepository;
import com.delivery.optimizer.repository.TourRepository;
import com.delivery.optimizer.repository.VehicleRepository;
import com.delivery.optimizer.repository.WarehouseRepository;
import com.delivery.optimizer.service.TourService;
import com.delivery.optimizer.util.DistanceCalculator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tours")
public class TourController {

    private final TourRepository tourRepository;
    private final VehicleRepository vehicleRepository;
    private final WarehouseRepository warehouseRepository;
    private final DeliveryRepository deliveryRepository;
    private final TourService tourService;

    public TourController(TourRepository tourRepository,
                          VehicleRepository vehicleRepository,
                          WarehouseRepository warehouseRepository,
                          DeliveryRepository deliveryRepository,
                          TourService tourService) {
        this.tourRepository = tourRepository;
        this.vehicleRepository = vehicleRepository;
        this.warehouseRepository = warehouseRepository;
        this.deliveryRepository = deliveryRepository;
        this.tourService = tourService;
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

    @PostMapping("/compare")
    public Map<String, Object> compare(@RequestBody Map<String, Object> body) {
        Long warehouseId = body.get("warehouseId") instanceof Number ? ((Number) body.get("warehouseId")).longValue() : null;
        Long vehicleId = body.get("vehicleId") instanceof Number ? ((Number) body.get("vehicleId")).longValue() : null;
        @SuppressWarnings("unchecked")
        List<Number> deliveryIdsNum = (List<Number>) body.get("deliveryIds");
        if (warehouseId == null || deliveryIdsNum == null || deliveryIdsNum.isEmpty()) {
            Map<String, Object> err = new HashMap<>();
            err.put("error", "warehouseId et deliveryIds sont requis");
            return err;
        }
      Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Vehicle vehicle = null;
        if (vehicleId != null) {
            vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle not found"));
        }

        List<Long> deliveryIds = new ArrayList<>();
        for (Number n : deliveryIdsNum) deliveryIds.add(n.longValue());
        List<Delivery> deliveries = deliveryRepository.findAllById(deliveryIds);
        if (deliveries.isEmpty()) {
            Map<String, Object> err = new HashMap<>();
            err.put("error", "Aucune delivery trouvée pour les IDs fournis");
            return err;
        }

        DistanceCalculator dc = new DistanceCalculator();
        TourOptimizer cw = new ClarkeWrightOptimizer(dc);

        long t1 = System.nanoTime();
        List<Delivery> cwRoute = cw.calculateOptimalTour(warehouse, deliveries);
        double cwDistance = tourService.getTotalDistance(warehouse, cwRoute);
        long t2 = System.nanoTime();

        long t3 = System.nanoTime();
        List<Delivery> nnRoute = nearestNeighbor(warehouse, deliveries, dc);
        double nnDistance = tourService.getTotalDistance(warehouse, nnRoute);
        long t4 = System.nanoTime();

        Map<String, Object> res = new HashMap<>();
        Map<String, Object> cwRes = new HashMap<>();
        cwRes.put("orderedDeliveryIds", cwRoute.stream().map(Delivery::getId).collect(Collectors.toList()));
        cwRes.put("totalDistance", cwDistance);
        cwRes.put("durationMs", (t2 - t1) / 1_000_000.0);

        Map<String, Object> nnRes = new HashMap<>();
        nnRes.put("orderedDeliveryIds", nnRoute.stream().map(Delivery::getId).collect(Collectors.toList()));
        nnRes.put("totalDistance", nnDistance);
        nnRes.put("durationMs", (t4 - t3) / 1_000_000.0);

        res.put("clarkeWright", cwRes);
        res.put("nearestNeighbor", nnRes);
        String winner;
        if (cwDistance < nnDistance) winner = "CLARKE_WRIGHT";
        else if (nnDistance < cwDistance) winner = "NEAREST_NEIGHBOR";
        else winner = ((t2 - t1) <= (t4 - t3)) ? "CLARKE_WRIGHT" : "NEAREST_NEIGHBOR";
        res.put("winner", winner);
        return res;
    }

    private List<Delivery> nearestNeighbor(Warehouse warehouse, List<Delivery> deliveries, DistanceCalculator dc) {
        List<Delivery> remaining = new ArrayList<>(deliveries);
        List<Delivery> route = new ArrayList<>();
        double curLat = warehouse.getLatitude();
        double curLon = warehouse.getLongitude();
        while (!remaining.isEmpty()) {
            Delivery best = null;
            double bestDist = Double.MAX_VALUE;
            for (Delivery d : remaining) {
                double dist = dc.calculateDistance(curLat, curLon, d.getLatitude(), d.getLongitude());
                if (dist < bestDist) {
                    bestDist = dist;
                    best = d;
                }
            }
            route.add(best);
            curLat = best.getLatitude();
            curLon = best.getLongitude();
            remaining.remove(best);
        }
        return route;
    }
}
