package com.hasitha.rentservice.service.impl;

import com.hasitha.rentcloud.model.customer.Customer;
import com.hasitha.rentcloud.model.rent.Rent;
import com.hasitha.rentcloud.model.vehicle.Vehicle;
import com.hasitha.rentservice.controller.RentController;
import com.hasitha.rentservice.hystrix.CustomerCommand;
import com.hasitha.rentservice.hystrix.VehicleCommand;
import com.hasitha.rentservice.model.FullRent;
import com.hasitha.rentservice.repository.RentRepository;
import com.hasitha.rentservice.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RentServiceImpl implements RentService {

    Logger logger= LoggerFactory.getLogger(RentServiceImpl.class);

    @Autowired
    RentRepository rentRepository;

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;


    @Override
    public Rent save(Rent rent) {
        return rentRepository.save(rent);
    }

    @Override
    public Rent fetchRentById(int id) {
       Optional<Rent> rent=rentRepository.findById(id);
        if(rent.isPresent()){
            return rent.get();
        }else{
            return null;
        }
    }

    @Override
    public FullRent fetchFullRentDetailsById(int id) {
        logger.info("Inside fetchFullRentDetailsById method of RentServiceImpl");
        FullRent fullRent= new FullRent();
       Rent rent= this.fetchRentById(id);
       try{
       if(rent!=null) {
           fullRent.setRent(rent);
           Customer customer = this.findCustomerById(rent.getCustomerId());
           if (customer != null) {
               fullRent.setCustomer(customer);
           }

           Vehicle vehicle = this.findVehicleById(rent.getVehicleId());
           if (vehicle != null) {
               fullRent.setVehicle(vehicle);
           }

           return fullRent;
       }else{
        return  null;
       }
       }catch(Exception ex){

           System.out.println(ex.getMessage());
       }
        return  null;
    }


    private Vehicle findVehicleById(int vehicleId) throws Exception{
        //Vehicle vehicle=restTemplate.getForObject("http://localhost:9192/services/vehicle/"+vehicleId,Vehicle.class);
        //Vehicle vehicle=restTemplate.getForObject("http://vehicle/services/vehicle/"+vehicleId,Vehicle.class);
        VehicleCommand vehicleCommand= new VehicleCommand(restTemplate,vehicleId);
        Vehicle vehicle=vehicleCommand.execute();
        return vehicle;

    }

    private Customer findCustomerById(int customerId) throws Exception{
        // Customer customer=restTemplate.getForObject("http://localhost:9191/services/customer/"+customerId,Customer.class);
        //Customer customer=restTemplate.getForObject("http://customer/services/customer/"+customerId,Customer.class);
        CustomerCommand customerCommand= new CustomerCommand(restTemplate,customerId);
        Customer customer=customerCommand.execute();
        return customer;

    }
}
