package com.logistics.ejb.remote;

import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.exception.ShipmentException;
import jakarta.ejb.Remote;

import java.util.Date;
import java.util.List;

@Remote
public interface ShipmentServiceRemote {
    void scheduleShipment(Shipment shipment) throws ShipmentException;
    List<Shipment> getAllShipments();
    List<Shipment> getUpcomingShipments(Date date);
    Shipment getShipmentById(Long id);
    void updateShipment(Shipment shipment) throws ShipmentException;
    void cancelShipment(Long id);
}
