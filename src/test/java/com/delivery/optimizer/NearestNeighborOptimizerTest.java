package com.delivery.optimizer;

import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.model.Warehouse;
import com.delivery.optimizer.optimizer.NearestNeighborOptimizer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NearestNeighborOptimizerTest {
@Test
    public void testCalculateOptimlTour(){
    Warehouse warehouse =new Warehouse();
    warehouse.setLatitude(35.6895);
    warehouse.setLongitude(-0.6412);

    List<Delivery> deliveries=new ArrayList<>();

    Delivery d1 =new Delivery();
    d1.setLatitude(35.6900);
    d1.setLongitude(-0.6400);


    Delivery d2 = new Delivery();
    d2.setLatitude(35.6950);
    d2.setLongitude(-0.6300);

    Delivery d3 = new Delivery();
    d3.setLatitude(35.7100);
    d3.setLongitude(-0.6000);

    deliveries.add(d1);
    deliveries.add(d2);
    deliveries.add(d3);


    NearestNeighborOptimizer optimizer = new NearestNeighborOptimizer();
    List<Delivery> ordered =optimizer.calculateOptimalTour(warehouse,deliveries);
    assertThat(ordered.size()).isEqualTo(3);

    assertThat(ordered.get(0)).isEqualTo(d1);
    System.out.println("=== Tournée optimisée ===");
    for (Delivery d : ordered) {
        System.out.printf("Lat: %.4f, Lon: %.4f%n", d.getLatitude(), d.getLongitude());
    }
}
}
