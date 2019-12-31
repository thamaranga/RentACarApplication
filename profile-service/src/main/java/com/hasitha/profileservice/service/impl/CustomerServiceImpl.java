package com.hasitha.profileservice.service.impl;

import com.hasitha.profileservice.repository.CustomerRepository;
import com.hasitha.profileservice.service.CustomerService;
import com.hasitha.rentcloud.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
