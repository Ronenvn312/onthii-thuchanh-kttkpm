package com.example.PassengerService.service;

import java.io.IOException;
import java.util.List;

import com.example.PassengerService.entity.Billing;
import com.example.PassengerService.entity.Passenger;

public interface PassengerService {
	public List<Passenger> findAll();
	public Passenger insertPassenger(Passenger passenger);
	public Billing getBillingByPassengerId(int passengerId);
	public Billing httpSearchBill(int passengerId) throws IOException;
}
