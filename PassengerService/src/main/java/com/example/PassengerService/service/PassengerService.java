package com.example.PassengerService.service;

import java.util.List;

import com.example.PassengerService.entity.Billing;
import com.example.PassengerService.entity.Passenger;

public interface PassengerService {
	public List<Passenger> findAll();
	public Passenger insertPassenger(Passenger passenger);
	public Billing getBillingByPassengerId(int passegerId);
}
