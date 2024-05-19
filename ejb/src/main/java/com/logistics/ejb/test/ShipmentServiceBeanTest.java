package com.logistics.ejb.test;

import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.exception.ShipmentException;
import com.logistics.ejb.service.ShipmentServiceBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentServiceBeanTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private ShipmentServiceBean shipmentService;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("WebPU");
        em = emf.createEntityManager();
        shipmentService = new ShipmentServiceBean();
        shipmentService.em = em;
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void testScheduleShipment() throws ShipmentException {
        Shipment shipment = new Shipment("Destination 1", "Destination 2", new Date());
        shipmentService.scheduleShipment(shipment);

        Shipment retrievedShipment = shipmentService.getShipmentById(shipment.getId());
        assertNotNull(retrievedShipment);
        assertEquals("Destination 1", retrievedShipment.getOrigin());
        assertEquals("Destination 2", retrievedShipment.getDestination());
    }

    @Test
    void testGetUpcomingShipments() {
        Date currentDate = new Date();
        shipmentService.scheduleShipment(new Shipment("Destination 1", "Destination 2", new Date(currentDate.getTime() + 86400000))); // Tomorrow (A day <)
        shipmentService.scheduleShipment(new Shipment("Destination 3", "Destination 4", new Date(currentDate.getTime() - 86400000))); // Yesterday (A day >)

        List<Shipment> upcomingShipments = shipmentService.getUpcomingShipments(currentDate);
        assertEquals(1, upcomingShipments.size());
        assertEquals("Destination 1", upcomingShipments.get(0).getOrigin());
        assertEquals("Destination 2", upcomingShipments.get(0).getDestination());
    }

}
