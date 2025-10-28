package com.delivery.optimizer.mapper;

import com.delivery.optimizer.dto.VehicleDTO;
import com.delivery.optimizer.model.Vehicle;
import com.delivery.optimizer.model.VehicleType;

public class VehicleMapper {

    public static VehicleDTO toDTO(Vehicle vehicle){
        VehicleDTO dto=new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setType(vehicle.getType().name());
        dto.setMaxWeight(vehicle.getMaxWeight());
        dto.setMaxVolume(vehicle.getMaxVolume());
        dto.setMaxDeliveries(vehicle.getMaxDeliveries());
        return dto;
    }
    public static Vehicle toEntity(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(dto.getId());
        vehicle.setType(VehicleType.valueOf(dto.getType().toString()));
        vehicle.setMaxWeight(dto.getMaxWeight());
        vehicle.setMaxVolume(dto.getMaxVolume());
        vehicle.setMaxDeliveries(dto.getMaxDeliveries());
        return vehicle;
    }
}
