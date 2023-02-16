package com.hasitha.customerservice.controller;

import com.hasitha.customerservice.service.CustomerService;
import com.hasitha.rentcloud.model.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/services")
public class CustomerController {

    Logger logger= LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/customer")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.save(customer);



    }

    @GetMapping(value = "/customer/{id}")
    public Customer fetchCustomerById(@PathVariable int id){
        logger.info("Inside fetchCustomerById method of CustomerController");
        return customerService.fetchCustomerById(id);


    }

    @GetMapping(value = "/customer")
    public List<Customer> fetchAllCustomers(){
        return customerService.fetchAllCustomers();



    }

    @PutMapping(value = "/customer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
        Customer existingCustomer=customerService.fetchCustomerById(customer.getId());
        if(existingCustomer==null){
            return new ResponseEntity<String>("No data foud for id "+ customer.getId(), HttpStatus.NOT_FOUND);
        }else{
            Customer cus=customerService.save(customer);
            if(cus==null){
                return new ResponseEntity<String>("Somethign went wrong while updating data", HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                return new ResponseEntity<Customer>(cus, HttpStatus.OK);
            }

        }
    }


    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable int id){
        Customer existingCustomer=customerService.fetchCustomerById(id);
        if(existingCustomer==null){
            return new ResponseEntity<String>("No data foud for id "+ id, HttpStatus.NOT_FOUND);
        }else{
            customerService.deleteCustomer(id);
            return new ResponseEntity<String>("successfully deleted.", HttpStatus.OK);
            }

        }

}
