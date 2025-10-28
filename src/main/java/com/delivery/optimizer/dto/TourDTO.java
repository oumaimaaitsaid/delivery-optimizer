package com.delivery.optimizer.dto;

import java.time.LocalDate;
import java.util.List;

public class TourDTO {
    private Long id;
    private LocalDate date;
    private Long vehicleId;
    private Long warehouseId;
    private List<Long> deliveryIds;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }

    public Long getWarehouseId() { return warehouseId; }
    public void setWarehouseId(Long warehouseId) { this.warehouseId = warehouseId; }

    public List<Long> getDeliveryIds() { return deliveryIds; }
    public void setDeliveryIds(List<Long> deliveryIds) { this.deliveryIds = deliveryIds; }
}
