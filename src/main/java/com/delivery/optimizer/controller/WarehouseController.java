package com.delivery.optimizer.controller;

import com.delivery.optimizer.model.Warehouse;
import com.delivery.optimizer.repository.WarehouseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseRepository warehouseRepository;
    public WarehouseController(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    //Get Recupérer tous les entrepôts
    @GetMapping
    public List<Warehouse> getAll(){
        return warehouseRepository.findAll();
    }






}
