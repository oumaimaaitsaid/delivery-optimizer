package com.delivery.optimizer.controller;

import com.delivery.optimizer.model.Tour;
import com.delivery.optimizer.repository.TourRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/tours")
public class TourController {

    private final TourRepository tourRepository;
    public TourController(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }
    //Récupérer toutes les tournés
    @GetMapping
    public List<Tour> getTours(){
        return tourRepository.findAll();
    }

    //Récuperer une tournée by Id

    @GetMapping("/{id}")
    public Tour getTourById(@PathVariable Long id){
        return tourRepository.findById(id).orElseThrow(()->new RuntimeException("Tour not found"));
    }

    //crée une nouvelle tourné
    @PostMapping
    public Tour createTour(@RequestBody Tour tour){
        return tourRepository.save(tour);
    }
    // Mettre à jour une tournée
    @PutMapping("/{id}")
    public Tour update(@PathVariable Long id, @RequestBody Tour updatedTour) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        tour.setDate(updatedTour.getDate());
        tour.setVehicle(updatedTour.getVehicle());
        tour.setWarehouse(updatedTour.getWarehouse());
        tour.setDeliveries(updatedTour.getDeliveries());

        return tourRepository.save(tour);
    }

    //DELETE — Supprimer une tournée
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tourRepository.deleteById(id);
    }
}
