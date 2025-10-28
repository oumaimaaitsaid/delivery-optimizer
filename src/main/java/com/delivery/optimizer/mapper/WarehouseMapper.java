package com.delivery.optimizer.mapper;

import com.delivery.optimizer.dto.WarehouseDTO;
import com.delivery.optimizer.model.Warehouse;

public class WarehouseMapper {

    public static WarehouseDTO toDTO(Warehouse warehouse){
        WarehouseDTO dto =new WarehouseDTO();
        dto.setId(warehouse.getId());
        dto.setName(warehouse.getName());
        dto.setLatitude(warehouse.getLatitude());
        dto.setLongitude(warehouse.getLongitude());
        dto.setOpeningHours(warehouse.getOpeningHours());
        return dto;


    }
    public static Warehouse toEntity(WarehouseDTO dto){
        Warehouse warehouse = new Warehouse();
        warehouse.setId(dto.getId());
        warehouse.setName(dto.getName());
        warehouse.setLatitude(dto.getLatitude());
        warehouse.setLongitude(dto.getLongitude());
        warehouse.setOpeningHours(dto.getOpeningHours());
        return warehouse;
    }
}
