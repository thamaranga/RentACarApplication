package com.hasitha.profileservice.service.impl;

import com.hasitha.profileservice.repository.CustomerRepository;
import com.hasitha.profileservice.service.CustomerService;
import com.hasitha.rentcloud.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer fetchCustomerById(int id) {
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }else{
        return null;
        }

    }

    @Override
    public List<Customer> fetchAllCustomers() {
        List<Customer> customers=null;
        customers=customerRepository.findAll();
        if(customers==null){
            customers=new ArrayList<Customer>();
        }
        return customers;

    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }


}
