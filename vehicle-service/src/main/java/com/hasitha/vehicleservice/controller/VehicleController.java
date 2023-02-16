package com.hasitha.vehicleservice.controller;



import com.hasitha.rentcloud.model.vehicle.Vehicle;
import com.hasitha.vehicleservice.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/services")
public class VehicleController {
    Logger logger= LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    VehicleService vehicleService;

    @PostMapping(value = "/vehicle")
    public Vehicle saveVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.save(vehicle);
    }

    @GetMapping(value = "/vehicle/{id}")
    public Vehicle fetchVehicleById(@PathVariable int id){
        logger.info("Inside fetchVehicleById method of VehicleController");
        return vehicleService.fetchVehicleById(id);
    }

    @GetMapping(value = "/vehicle")
    public List<Vehicle> fetchAllVehicles(){
        return vehicleService.fetchAllVehicles();

    }

    @PutMapping(value = "/vehicle")
    public ResponseEntity<?> updateVehicle(@RequestBody Vehicle vehicle){
        Vehicle existingVehicle=vehicleService.fetchVehicleById(vehicle.getId());
        if(existingVehicle==null){
            return new ResponseEntity<String>("No data foud for id "+ vehicle.getId(), HttpStatus.NOT_FOUND);
        }else{
            Vehicle veh=vehicleService.save(vehicle);
            if(veh==null){
                return new ResponseEntity<String>("Somethign went wrong while updating data", HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                return new ResponseEntity<Vehicle>(veh, HttpStatus.OK);
            }

        }
    }


    @DeleteMapping(value = "/vehicle/{id}")
    public ResponseEntity<?> deleteVehicleById(@PathVariable int id){
        Vehicle existingVehicle=vehicleService.fetchVehicleById(id);
        if(existingVehicle==null){
            return new ResponseEntity<String>("No data foud for id "+ id, HttpStatus.NOT_FOUND);
        }else{
            vehicleService.deleteVehicle(id);
            return new ResponseEntity<String>("successfully deleted.", HttpStatus.OK);
        }

    }

}
