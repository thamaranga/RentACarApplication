package com.hasitha.vehicleservice.controller;


import com.hasitha.rentcloud.model.Customer;
import com.hasitha.rentcloud.model.Vehicle;
import com.hasitha.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/services")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping(value = "/vehicle")
    public ResponseEntity<?> saveVehicle(@RequestBody Vehicle vehicle){
        Vehicle veh=vehicleService.save(vehicle);
        if(veh==null){
            return new ResponseEntity<String>("Somethignwent wrong while saving data", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<Vehicle>(veh, HttpStatus.CREATED);
        }


    }

    @GetMapping(value = "/vehicle/{id}")
    public ResponseEntity<?> fetchVehicleById(@PathVariable int id){
        Vehicle vehicle=vehicleService.fetchVehicleById(id);
        if(vehicle!=null){
            return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);


        }else{
            return new ResponseEntity<String>("No data foud for id "+ id, HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping(value = "/vehicle")
    public ResponseEntity<?> fetchAllVehicles(){
        List<Vehicle> vehicles= vehicleService.fetchAllVehicles();
        if(vehicles!=null && !vehicles.isEmpty()){
            return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No data foud", HttpStatus.NOT_FOUND);
        }


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
