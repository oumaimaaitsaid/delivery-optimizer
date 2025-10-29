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


}
