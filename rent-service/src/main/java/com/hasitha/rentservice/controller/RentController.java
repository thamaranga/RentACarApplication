package com.hasitha.rentservice.controller;

import com.hasitha.rentcloud.model.rent.Rent;
import com.hasitha.rentservice.model.FullRent;
import com.hasitha.rentservice.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/services")
public class RentController {

    Logger logger= LoggerFactory.getLogger(RentController.class);

    @Autowired
    RentService rentService;

    @PostMapping("/rent")
    public Rent save(@RequestBody Rent rent){
        logger.info("Inside save method of RentController");
        return rentService.save(rent);


    }

    @GetMapping("/rent/{id}")
    public Rent getRentById(@PathVariable int id){
        logger.info("Inside getRentById method of RentController");
        return rentService.fetchRentById(id);

    }

    @GetMapping("/rent/full/{id}")
    public FullRent getFullRentDetailsById(@PathVariable int id){
        logger.info("Inside getFullRentDetailsById method of RentController");
        return rentService.fetchFullRentDetailsById(id);



    }


}
