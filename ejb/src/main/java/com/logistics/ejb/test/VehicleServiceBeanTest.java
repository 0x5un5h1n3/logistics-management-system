package com.logistics.ejb.test;

import com.logistics.ejb.entity.Vehicle;
import com.logistics.ejb.service.VehicleServiceBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleServiceBeanTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private VehicleServiceBean vehicleService;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("WebPU");
        em = emf.createEntityManager();
        vehicleService = new VehicleServiceBean();
        vehicleService.em = em;
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void testRegisterVehicle() {
        Vehicle vehicle = new Vehicle("Truck", "ABC123", 10000.0);
        vehicleService.registerVehicle(vehicle);

        Vehicle retrievedVehicle = vehicleService.getVehicleById(vehicle.getId());
        assertNotNull(retrievedVehicle);
        assertEquals("Truck", retrievedVehicle.getType());
        assertEquals("ABC123", retrievedVehicle.getLicensePlate());
        assertEquals(10000.0, retrievedVehicle.getCapacity());
    }

    @Test
    void testUpdateVehicle() {
        Vehicle vehicle = new Vehicle("Truck", "ABC123", 10000.0);
        vehicleService.registerVehicle(vehicle);

        vehicle.setType("Van");
        vehicle.setLicensePlate("DEF456");
        vehicle.setCapacity(5000.0);
        vehicleService.updateVehicle(vehicle);

        Vehicle retrievedVehicle = vehicleService.getVehicleById(vehicle.getId());
        assertNotNull(retrievedVehicle);
        assertEquals("Van", retrievedVehicle.getType());
        assertEquals("DEF456", retrievedVehicle.getLicensePlate());
        assertEquals(5000.0, retrievedVehicle.getCapacity());
    }

}
