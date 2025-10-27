package com.delivery.optimizer;

import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.model.DeliveryStatus;
import com.delivery.optimizer.model.Warehouse;
import com.delivery.optimizer.optimizer.NearestNeighborOptimizer;
import com.delivery.optimizer.service.TourService;
import com.delivery.optimizer.util.DistanceCalculator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TourServiceTest {

    @Test
    public void testOptimizedTour() {

        TourService tourService = new TourService(
                new NearestNeighborOptimizer(),
                new DistanceCalculator()
        );

        Warehouse warehouse = new Warehouse(1L, "oumaima", 35.6895, -0.6412, "06:00-22:00");

        List<Delivery> deliveries = new ArrayList<>(Arrays.asList(
                new Delivery(1L, 35.70, -0.65, 1.5, 0.5, "06:00-22:00", DeliveryStatus.PENDING, null),
                new Delivery(2L, 35.72, -0.63, 2.0, 1.0, "08:00-12:00", DeliveryStatus.PENDING, null),
                new Delivery(3L, 35.68, -0.67, 1.0, 0.3, "09:00-11:00", DeliveryStatus.PENDING, null)
        ));

        List<Delivery> optimized = tourService.getOptimizedTour(warehouse, deliveries);
        double totalDistance = tourService.getTotalDistance(warehouse, optimized);

        System.out.println("Optimized route: " + optimized);
        System.out.println("Total distance: " + totalDistance + " km");
    }
}
