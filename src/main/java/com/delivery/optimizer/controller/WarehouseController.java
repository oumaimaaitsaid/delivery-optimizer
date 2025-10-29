package com.delivery.optimizer.controller;

import com.delivery.optimizer.dto.WarehouseDTO;
import com.delivery.optimizer.mapper.WarehouseMapper;
import com.delivery.optimizer.model.Warehouse;
import com.delivery.optimizer.repository.WarehouseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseRepository warehouseRepository;
    public WarehouseController(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    //Get Recupérer tous les entrepôts
    @GetMapping
    public List<WarehouseDTO> getAll(){
        return warehouseRepository.findAll()
                .stream()
                .map(WarehouseMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Get Recupérer les entreôts par ID
    @GetMapping("/{id}")
    public WarehouseDTO getById(@PathVariable Long id){
       Warehouse warehouse= warehouseRepository.findById(id).orElseThrow(()->new RuntimeException("Warehouse with id:"+id+" not found"));
       return WarehouseMapper.toDTO(warehouse);
    }

    //Post Ajouter un nouvel entrepôt

    @PostMapping
    public WarehouseDTO create(@RequestBody WarehouseDTO dto){
        Warehouse warehouse= WarehouseMapper.toEntity(dto);
        Warehouse saved= warehouseRepository.save(warehouse);
        return WarehouseMapper.toDTO(saved);
    }

    //PUT modifier un entrepôt

    @PutMapping("/{id}")

    public WarehouseDTO update(@PathVariable Long id,@RequestBody WarehouseDTO dto){
        Warehouse warehouse =warehouseRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Warehouse with id:"+id+" not found"));
        warehouse.setName(dto.getName());
        warehouse.setLatitude(dto.getLatitude());
        warehouse.setLongitude(dto.getLongitude());
        warehouse.setOpeningHours(dto.getOpeningHours());

         Warehouse updated =warehouseRepository.save(warehouse);
        return  WarehouseMapper.toDTO(updated);

    }
    //Supprimer un entrepôt
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        warehouseRepository.deleteById(id);
    }
}
