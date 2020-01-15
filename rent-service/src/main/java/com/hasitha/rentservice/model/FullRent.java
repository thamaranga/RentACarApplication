package com.hasitha.rentservice.model;

import com.hasitha.rentcloud.model.customer.Customer;
import com.hasitha.rentcloud.model.rent.Rent;
import com.hasitha.rentcloud.model.vehicle.Vehicle;

public class FullRent {

    private Rent rent;
    private Customer customer;
    private Vehicle vehicle;


    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
