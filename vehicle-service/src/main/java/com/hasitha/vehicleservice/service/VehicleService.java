package com.hasitha.vehicleservice.service;

import com.hasitha.rentcloud.model.Customer;
import com.hasitha.rentcloud.model.Vehicle;

import java.util.List;

public interface VehicleService {

    Vehicle save(Vehicle vehicle);
    Vehicle fetchVehicleById(int id);
    List<Vehicle> fetchAllVehicles();
    void deleteVehicle(int id);
}
