package com.example.PassengerService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PassengerService.entity.Passenger;

@Repository
public interface PassengerRespository extends JpaRepository<Passenger, Integer>{

}
