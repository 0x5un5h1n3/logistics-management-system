package com.logistics.ejb.remote;

import com.logistics.ejb.entity.Vehicle;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface VehicleServiceRemote {
    void registerVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(Long id);
    void updateVehicle(Vehicle vehicle);
    void deregisterVehicle(Long id);
}

