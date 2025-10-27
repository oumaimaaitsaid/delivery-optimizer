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
    private double distance(double lat1, double long1, double lat2,double long2) {
        double R =6371;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(long2-long1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2)
                +Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng/2) * Math.sin(dLng/2);
        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    }
}
