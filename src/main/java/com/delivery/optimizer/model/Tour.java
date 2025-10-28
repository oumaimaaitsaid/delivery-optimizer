package com.delivery.optimizer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Driver;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    private  Vehicle  vehicle;

    @ManyToOne
    private Warehouse warehouse;

    @OneToMany(mappedBy ="tour" ,cascade = CascadeType.ALL)
    private List<Delivery> deliveries =new ArrayList<>();
}
