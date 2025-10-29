package com.delivery.optimizer.service;

import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.model.Vehicle;
import com.delivery.optimizer.model.Warehouse;
import com.delivery.optimizer.optimizer.TourOptimizer;
import com.delivery.optimizer.util.DistanceCalculator;


import java.util.List;

public class TourService {
    private final TourOptimizer optimizer;
   private  final DistanceCalculator  distanceCalculator;
    public TourService(TourOptimizer optimizer,DistanceCalculator distanceCalculator) {
        this.optimizer =optimizer;
        this.distanceCalculator = distanceCalculator;
    }

    public List<Delivery> getOptimizedTour(Warehouse warehouse,List<Delivery>  deliveries){
        return optimizer.calculateOptimalTour(warehouse,deliveries);
    }

    public List<Delivery> getOptimizedTour(Warehouse warehouse, Vehicle vehicle, List<Delivery> deliveries){
        if (deliveries == null || deliveries.isEmpty()) return deliveries;
        if (vehicle == null) return optimizer.calculateOptimalTour(warehouse, deliveries);

        int count = deliveries.size();
        if (vehicle.getMaxDeliveries() > 0 && count > vehicle.getMaxDeliveries()) {
            throw new IllegalArgumentException("Nombre de livraisons dépasse la capacité du véhicule");
        }

        double totalWeight = 0.0;
        double totalVolume = 0.0;
        for (Delivery d : deliveries) {
            totalWeight += d.getWeight();
            totalVolume += d.getVolume();
        }

        if (vehicle.getMaxWeight() > 0 && totalWeight > vehicle.getMaxWeight()) {
            throw new IllegalArgumentException("Poids total dépasse la capacité du véhicule");
        }
        if (vehicle.getMaxVolume() > 0 && totalVolume > vehicle.getMaxVolume()) {
            throw new IllegalArgumentException("Volume total dépasse la capacité du véhicule");
        }

        return optimizer.calculateOptimalTour(warehouse, deliveries);
    }

    public double getTotalDistance(Warehouse warehouse,List<Delivery> orderedDeliveries){
        if(orderedDeliveries == null||orderedDeliveries.isEmpty()) return 0.0;
        double totalDistance = 0.0;
        double currentLat = warehouse.getLatitude();
        double currentLon = warehouse.getLongitude();

        for(Delivery d :orderedDeliveries){
            totalDistance += distanceCalculator.calculateDistance(currentLat,currentLon,d.getLatitude(),d.getLongitude());
            currentLat = d.getLatitude();
            currentLon = d.getLongitude();
        }

        totalDistance += distanceCalculator.calculateDistance(currentLat, currentLon, warehouse.getLatitude(), warehouse.getLongitude());
        return   totalDistance;
    }
}
