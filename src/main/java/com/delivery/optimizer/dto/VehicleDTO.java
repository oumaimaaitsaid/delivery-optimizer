package com.delivery.optimizer.dto;

import com.delivery.optimizer.model.VehicleType;

public class VehicleDTO {
    private Long id;
    private String type;
    private double maxWeight;
    private double maxVolume;
    private int maxDeliveries;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VehicleType getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getMaxWeight() { return maxWeight; }
    public void setMaxWeight(double maxWeight) { this.maxWeight = maxWeight; }

    public double getMaxVolume() { return maxVolume; }
    public void setMaxVolume(double maxVolume) { this.maxVolume = maxVolume; }

    public int getMaxDeliveries() { return maxDeliveries; }
    public void setMaxDeliveries(int maxDeliveries) { this.maxDeliveries = maxDeliveries; }
}
