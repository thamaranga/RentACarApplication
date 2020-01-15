package com.hasitha.vehicleservice.service.impl;


import com.hasitha.rentcloud.model.vehicle.Vehicle;
import com.hasitha.vehicleservice.repository.VehicleRepository;
import com.hasitha.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle fetchVehicleById(int id) {
        Optional<Vehicle> vehicle=vehicleRepository.findById(id);
        if(vehicle.isPresent()){
            return vehicle.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Vehicle> fetchAllVehicles() {
        List<Vehicle> vehicles=null;
        vehicles=vehicleRepository.findAll();
        if(vehicles==null){
            vehicles=new ArrayList<Vehicle>();
        }
        return vehicles;

    }

    @Override
    public void deleteVehicle(int id) {
        vehicleRepository.deleteById(id);
    }
}
