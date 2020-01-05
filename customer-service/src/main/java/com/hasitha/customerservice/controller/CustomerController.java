package com.hasitha.customerservice.controller;

import com.hasitha.customerservice.service.CustomerService;
import com.hasitha.rentcloud.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/services")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/customer")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer){
        Customer cus=customerService.save(customer);
        if(cus==null){
            return new ResponseEntity<String>("Somethignwent wrong while saving data", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<Customer>(cus, HttpStatus.CREATED);
        }


    }

    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<?> fetchCustomerById(@PathVariable int id){
        Customer customer=customerService.fetchCustomerById(id);
        if(customer!=null){
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);


        }else{
            return new ResponseEntity<String>("No data foud for id "+ id, HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping(value = "/customer")
    public ResponseEntity<?> fetchAllCustomers(){
        List<Customer> customers= customerService.fetchAllCustomers();
        if(customers!=null && !customers.isEmpty()){
            return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No data foud", HttpStatus.NOT_FOUND);
        }


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
