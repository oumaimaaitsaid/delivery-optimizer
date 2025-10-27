package com.delivery.optimizer.controller;

import com.delivery.optimizer.model.Tour;
import com.delivery.optimizer.repository.TourRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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




}
