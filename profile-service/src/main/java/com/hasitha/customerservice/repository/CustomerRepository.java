package com.hasitha.profileservice.repository;

import com.hasitha.rentcloud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer , Integer> {


}
