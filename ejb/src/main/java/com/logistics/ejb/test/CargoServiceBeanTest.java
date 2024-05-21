package com.logistics.ejb.test;

import com.logistics.ejb.entity.Cargo;
import com.logistics.ejb.exception.CargoException;
import com.logistics.ejb.service.CargoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CargoServiceBeanTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private CargoService cargoService;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("WebPU");
        em = emf.createEntityManager();
        cargoService = new CargoService();
        cargoService.em = em;
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void testCreateCargo() throws CargoException {
        Cargo cargo = new Cargo("Test Cargo", 100.0);
        cargoService.createCargo(cargo);

        Cargo retrievedCargo = cargoService.getCargoById(cargo.getId());
        assertNotNull(retrievedCargo);
        assertEquals("Test Cargo", retrievedCargo.getDescription());
        assertEquals(100.0, retrievedCargo.getWeight());
    }

    @Test
    void testUpdateCargo() throws CargoException {
        Cargo cargo = new Cargo("Test Cargo", 100.0);
        cargoService.createCargo(cargo);

        cargo.setDescription("Updated Cargo");
        cargo.setWeight(200.0);
        cargoService.updateCargo(cargo);

        Cargo retrievedCargo = cargoService.getCargoById(cargo.getId());
        assertNotNull(retrievedCargo);
        assertEquals("Updated Cargo", retrievedCargo.getDescription());
        assertEquals(200.0, retrievedCargo.getWeight());
    }

}
