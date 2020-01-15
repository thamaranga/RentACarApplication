package com.hasitha.rentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hasitha.rentcloud.model.rent.Rent;

public interface RentRepository  extends JpaRepository<Rent, Integer> {
}
