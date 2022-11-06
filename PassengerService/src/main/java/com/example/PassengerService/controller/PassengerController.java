package com.example.PassengerService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PassengerService.entity.Billing;
import com.example.PassengerService.entity.Passenger;
import com.example.PassengerService.service.PassengerService;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
	
	
	@Autowired
	private PassengerService passengerService;
	
	@GetMapping("/find-all")
	public List<Passenger> findAll() {
		return passengerService.findAll();
	}
	
	@PostMapping("/insert")
	public Passenger insertPassenger(@RequestBody Passenger passenger) {
		return passengerService.insertPassenger(passenger);
	}
	@GetMapping("/billing")
	public Billing getBillingByPassengerId(@RequestParam("passengerId") int passengerId) {
		return passengerService.getBillingByPassengerId(passengerId);
	}
}
