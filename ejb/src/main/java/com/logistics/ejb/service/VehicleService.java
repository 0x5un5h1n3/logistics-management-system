package com.logistics.ejb.service;

import com.logistics.ejb.entity.Vehicle;
import com.logistics.ejb.remote.VehicleServiceRemote;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
@Remote(VehicleServiceRemote.class)
public class VehicleService implements VehicleServiceRemote {

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void registerVehicle(Vehicle vehicle) {
        em.persist(vehicle);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Vehicle> getAllVehicles() {
        return em.createQuery("SELECT v FROM Vehicle v", Vehicle.class).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Vehicle getVehicleById(Long id) {
        return em.find(Vehicle.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateVehicle(Vehicle vehicle) {
        em.merge(vehicle);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deregisterVehicle(Long id) {
        Vehicle vehicle = em.find(Vehicle.class, id);
        if (vehicle != null) {
            em.remove(vehicle);
        }
    }
}
