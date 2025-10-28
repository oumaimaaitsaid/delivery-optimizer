package com.delivery.optimizer;

import com.delivery.optimizer.model.Delivery;
import com.delivery.optimizer.model.DeliveryStatus;
import com.delivery.optimizer.repository.DeliveryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = DeliveryOptimizerApplication.class)
public class DeliveryServiceTest {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    public void testSaveDelivery() {
        Delivery d = new Delivery();
        d.setLatitude(35.6895);
        d.setLongitude(-0.6412);
        d.setWeight(10.0);
        d.setVolume(1.2);
        d.setStatus(DeliveryStatus.PENDING);
        deliveryRepository.save(d);

        assertFalse(deliveryRepository.findAll().isEmpty());
    }
}
