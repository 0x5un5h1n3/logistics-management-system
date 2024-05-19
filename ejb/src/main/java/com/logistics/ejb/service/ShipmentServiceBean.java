package com.logistics.ejb.service;

import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.exception.ShipmentException;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
@Remote(ShipmentService.class)
public class ShipmentServiceBean implements ShipmentService {
    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void scheduleShipment(Shipment shipment) throws ShipmentException {
        if (shipment.getShippingDate().before(new Date())) {
            throw new ShipmentException("Shipping date cannot be in the past");
        }
        em.persist(shipment);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Shipment> getAllShipments() {
        return em.createQuery("SELECT s FROM Shipment s JOIN FETCH s.cargos", Shipment.class).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Shipment> getUpcomingShipments(Date date) {
        return em.createQuery("SELECT s FROM Shipment s WHERE s.shippingDate >= :date", Shipment.class)
                .setParameter("date", date)
                .getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Shipment getShipmentById(Long id) {
        return em.find(Shipment.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateShipment(Shipment shipment) throws ShipmentException {
        if (shipment.getShippingDate().before(new Date())) {
            throw new ShipmentException("Shipping date cannot be in the past");
        }
        em.merge(shipment);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void cancelShipment(Long id) {
        Shipment shipment = em.find(Shipment.class, id);
        if (shipment != null) {
            em.remove(shipment);
        }
    }
}
