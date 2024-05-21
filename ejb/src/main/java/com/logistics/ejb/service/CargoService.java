package com.logistics.ejb.service;

import com.logistics.ejb.entity.Cargo;
import com.logistics.ejb.exception.CargoException;
import com.logistics.ejb.remote.CargoServiceRemote;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(CargoServiceRemote.class)
public class CargoService implements CargoServiceRemote {
    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createCargo(Cargo cargo) throws CargoException {
        if (cargo.getWeight() <= 0) {
            throw new CargoException("Cargo weight must be positive");
        }
        em.persist(cargo);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Cargo> getAllCargo() {
        return em.createQuery("SELECT c FROM Cargo c", Cargo.class).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Cargo getCargoById(Long id) {
        return em.find(Cargo.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCargo(Cargo cargo) throws CargoException {
        if (cargo.getWeight() <= 0) {
            throw new CargoException("Cargo weight must be positive");
        }
        em.merge(cargo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCargo(Long id) {
        Cargo cargo = em.find(Cargo.class, id);
        if (cargo != null) {
            em.remove(cargo);
        }
    }
}
