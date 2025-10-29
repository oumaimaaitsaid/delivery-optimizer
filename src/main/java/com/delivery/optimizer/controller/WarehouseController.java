package com.delivery.optimizer.controller;

import com.delivery.optimizer.dto.WarehouseDTO;
import com.delivery.optimizer.mapper.WarehouseMapper;
import com.delivery.optimizer.model.Warehouse;
import com.delivery.optimizer.repository.WarehouseRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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
       Warehouse warehouse= warehouseRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse with id:"+id+" not found"));
       return WarehouseMapper.toDTO(warehouse);
    }

    //Post Ajouter un nouvel entrepôt

    @PostMapping
    public WarehouseDTO create(@RequestBody WarehouseDTO dto){
        validate(dto);
        Warehouse warehouse= WarehouseMapper.toEntity(dto);
        Warehouse saved= warehouseRepository.save(warehouse);
        return WarehouseMapper.toDTO(saved);
    }

    //PUT modifier un entrepôt

    @PutMapping("/{id}")

    public WarehouseDTO update(@PathVariable Long id,@RequestBody WarehouseDTO dto){
        validate(dto);
        Warehouse warehouse =warehouseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse with id:"+id+" not found"));
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
        if (!warehouseRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse with id:"+id+" not found");
        }
        warehouseRepository.deleteById(id);
    }

    private void validate(WarehouseDTO dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payload manquant");
        }
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le nom de l'entrepôt est obligatoire");
        }
        double lat = dto.getLatitude();
        double lon = dto.getLongitude();
        if (lat < -90.0 || lat > 90.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Latitude invalide (-90 à 90)");
        }
        if (lon < -180.0 || lon > 180.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Longitude invalide (-180 à 180)");
        }
        if (dto.getOpeningHours() == null || !dto.getOpeningHours().matches("\\d{2}:\\d{2}-\\d{2}:\\d{2}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Horaires au format HH:mm-HH:mm requis (ex: 06:00-22:00)");
        }
        String[] parts = dto.getOpeningHours().split("-");
        try {
            LocalTime start = LocalTime.parse(parts[0]);
            LocalTime end = LocalTime.parse(parts[1]);
            if (!start.isBefore(end)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'heure d'ouverture doit être avant l'heure de fermeture");
            }
            LocalTime min = LocalTime.parse("06:00");
            LocalTime max = LocalTime.parse("22:00");
            if (start.isBefore(min) || end.isAfter(max)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Horaires doivent être dans la plage 06:00-22:00");
            }
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Horaires invalides");
        }
    }
}
