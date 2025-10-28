package com.delivery.optimizer.mapper;

import com.delivery.optimizer.dto.VehicleDTO;
import com.delivery.optimizer.model.Vehicle;

public class VehicleMapper {

    public static VehicleDTO toDTO(Vehicle vehicle){
        VehicleDTO dto=new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setType(String.valueOf(vehicle.getType()));
        dto.setMaxWeight(vehicle.getMaxWeight());
        dto.setMaxVolume(vehicle.getMaxVolume());
        dto.setMaxDeliveries(vehicle.getMaxDeliveries());
        return dto;
    }
    public static Vehicle toEntity(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(dto.getId());
        vehicle.setType(dto.getType());
        vehicle.setMaxWeight(dto.getMaxWeight());
        vehicle.setMaxVolume(dto.getMaxVolume());
        vehicle.setMaxDeliveries(dto.getMaxDeliveries());
        return vehicle;
    }
}
