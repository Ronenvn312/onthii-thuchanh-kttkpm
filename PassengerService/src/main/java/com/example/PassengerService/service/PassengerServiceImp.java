package com.example.PassengerService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.PassengerService.dao.PassengerRespository;
import com.example.PassengerService.entity.Billing;
import com.example.PassengerService.entity.Passenger;

@Service
public class PassengerServiceImp implements PassengerService{
	static String url_billing = "http://localhost:9080/billing";
	private static RestTemplate RestTemplate = new RestTemplate();
	@Autowired
	private PassengerRespository passengerRespository;

	@Override
	public List<Passenger> findAll() {
		// TODO Auto-generated method stub
		return passengerRespository.findAll();
	}

	@Override
	public Passenger insertPassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		return passengerRespository.save(passenger);
	}

	@Override
	public Billing getBillingByPassengerId(int passengerId) {
		Billing billing = RestTemplate.getForEntity(url_billing+"/find?passengerId="+passengerId, Billing.class).getBody();
		return billing;
	}
	

}
