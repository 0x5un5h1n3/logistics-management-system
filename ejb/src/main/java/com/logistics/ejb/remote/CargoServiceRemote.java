package com.logistics.ejb.remote;

import com.logistics.ejb.entity.Cargo;
import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.exception.CargoException;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface CargoServiceRemote {
    void createCargo(Cargo cargo, Shipment shipment) throws CargoException;
    void updateCargo(Cargo cargo) throws CargoException;
    void deleteCargo(Long id);
    Cargo getCargoById(Long id);
    List<Cargo> getCargosByShipment(Shipment shipment);
}
