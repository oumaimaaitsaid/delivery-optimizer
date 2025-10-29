package com.delivery.optimizer.model;
import javax.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WAREHOUSE")
public class Warehouse {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double latitude;
    private double longitude;
    private String openingHours;
}
