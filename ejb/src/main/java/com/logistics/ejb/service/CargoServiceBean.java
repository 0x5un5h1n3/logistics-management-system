package com.logistics.ejb.service;

import com.logistics.ejb.entity.Cargo;
import com.logistics.ejb.exception.CargoException;
import com.logistics.ejb.remote.CargoService;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(CargoService.class)
public class CargoServiceBean implements CargoService {
    private static final Logger logger = LoggerFactory.getLogger(CargoService.class);

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createCargo(Cargo cargo) throws CargoException {
        try {
            if (cargo.getWeight() <= 0) {
                throw new CargoException("Cargo weight must be positive");
            }
            em.persist(cargo);
        } catch (Exception e) {
            logger.error("Error during cargo creation", e);
            throw new CargoException("Error during cargo creation: " + e.getMessage());
        }
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
        em.remove(cargo);
    }
}