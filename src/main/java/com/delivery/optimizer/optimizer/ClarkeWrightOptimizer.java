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

        class Saving { final Delivery i; final Delivery j; final double s; Saving(Delivery i, Delivery j, double s){this.i=i;this.j=j;this.s=s;} }

        List<Saving> savings = new ArrayList<>();
        for (int a = 0; a < deliveries.size(); a++) {
            for (int b = a + 1; b < deliveries.size(); b++) {
                Delivery i = deliveries.get(a);
                Delivery j = deliveries.get(b);
                double sij = dw.get(i) + dw.get(j) - distanceCalculator.calculateDistance(i, j);
                savings.add(new Saving(i, j, sij));
            }
        }
        savings.sort((x, y) -> Double.compare(y.s, x.s));

        List<LinkedList<Delivery>> routes = new ArrayList<>();
        Map<Delivery, LinkedList<Delivery>> routeOf = new HashMap<>();
        for (Delivery d : deliveries) {
            LinkedList<Delivery> r = new LinkedList<>();
            r.add(d);
            routes.add(r);
            routeOf.put(d, r);
        }


    }
}
