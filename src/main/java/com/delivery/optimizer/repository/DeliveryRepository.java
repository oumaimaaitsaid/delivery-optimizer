package com.delivery.optimizer.repository;

import com.delivery.optimizer.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {


}
