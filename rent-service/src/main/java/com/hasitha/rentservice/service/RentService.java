package com.hasitha.rentservice.service;

import com.hasitha.rentcloud.model.rent.Rent;
import com.hasitha.rentservice.model.FullRent;


public interface RentService {

    Rent save(Rent rent);
    Rent fetchRentById(int id);
    FullRent fetchFullRentDetailsById(int id);
}
