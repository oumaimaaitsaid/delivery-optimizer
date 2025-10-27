package com.delivery.optimizer.optimizer;

import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.model.Warehouse;
import java.util.*;
public class NearestNeighborOptimizer implements TourOptimizer{

    @Override
    public List<Delivery> calculateOptimalTour(Warehouse warehouse, List<Delivery> deliveries) {
        List<Delivery> remaining = new ArrayList<>(deliveries);
        List<Delivery> result= new ArrayList<>();


        double currentLat = warehouse.getLatitude();
        double currentLon = warehouse.getLongitude();

        while (!remaining.isEmpty()) {
            Delivery nearest = remaining.stream()
                    .min(Comparator.comparingDouble(d -> distance(currentLat, currentLon, d.getLatitude(), d.getLongitude())))
                    .orElseThrow();
            result.add(nearest);
            currentLat = nearest.getLatitude();
            currentLon = nearest.getLongitude();
            remaining.remove(nearest);
        }

        return result;
    }

}
