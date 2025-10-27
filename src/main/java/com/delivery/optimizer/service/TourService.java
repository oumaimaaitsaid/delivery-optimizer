package com.delivery.optimizer.service;

import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.model.Warehouse;
import com.delivery.optimizer.optimizer.TourOptimizer;

import java.util.List;

public class TourService {
    private final TourOptimizer optimizer;

    public TourService(TourOptimizer optimizer) {
        this.optimizer =optimizer;
    }

    public List<Delivery> getOptimizedTour(Warehouse warehouse,List<Delivery>  deliveries){
        return optimizer.calculateOptimalTour(warehouse,deliveries);
    }
}
