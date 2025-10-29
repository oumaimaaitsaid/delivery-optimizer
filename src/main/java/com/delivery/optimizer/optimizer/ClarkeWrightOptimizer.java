package com.delivery.optimizer.optimizer;

import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.model.Warehouse;
import com.delivery.optimizer.util.DistanceCalculator;

import java.util.*;

public class ClarkeWrightOptimizer implements TourOptimizer {
    private final DistanceCalculator distanceCalculator;

    public ClarkeWrightOptimizer(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    @Override
    public List<Delivery> calculateOptimalTour(Warehouse warehouse, List<Delivery> deliveries) {
        if (deliveries == null || deliveries.size() <= 1) {
            return deliveries == null ? Collections.emptyList() : new ArrayList<>(deliveries);
        }

        Map<Delivery, Double> dw = new HashMap<>();
        for (Delivery d : deliveries) {
            dw.put(d, distanceCalculator.calculateDistance(warehouse, d));
        }


    }
}
