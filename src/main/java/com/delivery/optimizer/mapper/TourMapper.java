package com.delivery.optimizer.mapper;

import com.delivery.optimizer.dto.TourDTO;
import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.model.Tour;
import java.util.stream.Collectors;

public class TourMapper {

    public static TourDTO toDTO(Tour tour){
        TourDTO dto = new TourDTO();
        dto.setId(tour.getId());
        dto.setDate(tour.getDate());
        dto.setVehicleId(tour.getVehicle() != null ? tour.getVehicle().getId() : null);
        dto.setWarehouseId(tour.getWarehouse() != null ? tour.getWarehouse().getId() : null);
        dto.setDeliveryIds(
                tour.getDeliveries().stream()
                        .map(Delivery::getId)
                        .collect(Collectors.toList())
        );
        return dto;
    }
    public static Tour toEntity(TourDTO dto ) {
        Tour tour = new Tour();
        tour.setId(dto.getId());
        tour.setDate(dto.getDate());

        return tour;
    }

}
