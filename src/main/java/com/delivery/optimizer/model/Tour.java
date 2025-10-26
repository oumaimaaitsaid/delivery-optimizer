package com.delivery.optimizer.model;

import jakarta.persistence.*;

import java.sql.Driver;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
