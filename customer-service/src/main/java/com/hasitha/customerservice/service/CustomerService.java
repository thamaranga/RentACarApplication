package com.hasitha.customerservice.service;

import com.hasitha.rentcloud.model.customer.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);
    Customer fetchCustomerById(int id);
    List<Customer> fetchAllCustomers();
    void deleteCustomer(int id);

}
