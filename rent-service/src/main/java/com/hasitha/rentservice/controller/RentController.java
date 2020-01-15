package com.hasitha.rentservice.controller;

import com.hasitha.rentcloud.model.rent.Rent;
import com.hasitha.rentservice.model.FullRent;
import com.hasitha.rentservice.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/services")
public class RentController {

    @Autowired
    RentService rentService;

    @PostMapping("/rent")
    public Rent save(@RequestBody Rent rent){


        return rentService.save(rent);


    }

    @GetMapping("/rent/{id}")
    public Rent getRentById(@PathVariable int id){
        return rentService.fetchRentById(id);

    }

    @GetMapping("/rent/full/{id}")
    public FullRent getFullRentDetailsById(@PathVariable int id){
        return rentService.fetchFullRentDetailsById(id);



    }


}
