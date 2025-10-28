package com.delivery.optimizer;

import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.model.DeliveryStatus;
import com.delivery.optimizer.model.Warehouse;
import com.delivery.optimizer.optimizer.NearestNeighborOptimizer;
import com.delivery.optimizer.service.TourService;
import com.delivery.optimizer.util.DistanceCalculator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TourServiceTest {

    @Test
    public void testOptimizedTour() {

        // Initialisation du service
        TourService tourService = new TourService(
                new NearestNeighborOptimizer(),
                new DistanceCalculator()
        );

        // Création d'un entrepôt fictif
        Warehouse warehouse = new Warehouse();
        warehouse.setId(1L);
        warehouse.setName("Oumaima");
        warehouse.setLatitude(35.6895);
        warehouse.setLongitude(-0.6412);
        warehouse.setOpeningHours("06:00-22:00");

        // Création des livraisons avec le constructeur vide + setters
        List<Delivery> deliveries = new ArrayList<>();

        Delivery d1 = new Delivery();
        d1.setId(1L);
        d1.setLatitude(35.70);
        d1.setLongitude(-0.65);
        d1.setWeight(1.5);
        d1.setVolume(0.5);
        d1.setTimeWindow("06:00-22:00");
        d1.setStatus(DeliveryStatus.FAILED);
        d1.setTour(null);

        Delivery d2 = new Delivery();
        d2.setId(2L);
        d2.setLatitude(35.72);
        d2.setLongitude(-0.63);
        d2.setWeight(2.0);
        d2.setVolume(1.0);
        d2.setTimeWindow("08:00-12:00");
        d2.setStatus(DeliveryStatus.PENDING);
        d2.setTour(null);

        Delivery d3 = new Delivery();
        d3.setId(3L);
        d3.setLatitude(35.68);
        d3.setLongitude(-0.67);
        d3.setWeight(1.0);
        d3.setVolume(0.3);
        d3.setTimeWindow("09:00-11:00");
        d3.setStatus(DeliveryStatus.PENDING);
        d3.setTour(null);

        deliveries.add(d1);
        deliveries.add(d2);
        deliveries.add(d3);

        // Calcul du parcours optimisé
        List<Delivery> optimized = tourService.getOptimizedTour(warehouse, deliveries);

        // Calcul de la distance totale
        double totalDistance = tourService.getTotalDistance(warehouse, optimized);

        // Affichage du résultat
        System.out.println("Optimized route: " + optimized);
        System.out.println("Total distance: " + totalDistance + " km");
    }
}
