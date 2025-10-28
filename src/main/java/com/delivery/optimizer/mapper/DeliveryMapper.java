package com.delivery.optimizer.mapper;

import com.delivery.optimizer.dto.DeliveryDTO;
import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.model.Tour;

public class DeliveryMapper {

    public static DeliveryDTO toDTO(Delivery delivery) {
        DeliveryDTO dto = new DeliveryDTO();
        dto.setId(delivery.getId());
        dto.setLatitude(delivery.getLatitude());
        dto.setLongitude(delivery.getLongitude());
        dto.setWeight(delivery.getWeight());
        dto.setVolume(delivery.getVolume());
        dto.setTimeWindow(delivery.getTimeWindow());
        dto.setStatus(delivery.getStatus());
        dto.setTourId(delivery.getTour() != null ? delivery.getTour().getId() : null);
        return dto;

    }
    public static Delivery toEntity(DeliveryDTO dto) {
        Delivery delivery = new Delivery();
        delivery.setId(dto.getId());
        delivery.setLatitude(dto.getLatitude());
        delivery.setLongitude(dto.getLongitude());
        delivery.setWeight(dto.getWeight());
        delivery.setVolume(dto.getVolume());
        delivery.setTimeWindow(dto.getTimeWindow());
        delivery.setStatus(dto.getStatus());

        return delivery;
    }

}
