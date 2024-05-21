package com.logistics.ejb.remote;

import com.logistics.ejb.entity.Cargo;
import com.logistics.ejb.exception.CargoException;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface CargoServiceRemote {
    void createCargo(Cargo cargo) throws CargoException;
    List<Cargo> getAllCargo();
    Cargo getCargoById(Long id);
    void updateCargo(Cargo cargo) throws CargoException;
    void deleteCargo(Long id);
}