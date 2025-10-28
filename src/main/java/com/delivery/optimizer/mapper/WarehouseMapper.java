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

}
