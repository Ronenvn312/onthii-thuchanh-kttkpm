package com.example.PassengerService.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.PassengerService.dao.PassengerRespository;
import com.example.PassengerService.entity.Billing;
import com.example.PassengerService.entity.Passenger;
import com.example.PassengerService.service.failures.NoFailure;
import com.example.PassengerService.service.failures.PotentialFailure;


@Service
public class PassengerServiceImp implements PassengerService{
	PotentialFailure potentialFailure = new NoFailure();
	
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
	@Override
	public Billing httpSearchBill(int passengerId) throws IOException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss SSS");
		 System.out.println("Searching for flights; current time = " + LocalDateTime.now().format(formatter));
		
		 Billing billing = new Billing();
	
			 billing = RestTemplate.getForEntity(url_billing+"/find?passengerId="+passengerId, Billing.class).getBody();
				if (billing ==null ) {
					System.out.println("Billing data initialization in progress, cannot search !");
					Billing billing2 = new Billing(0, "no bill", 0, passengerId);
		            return billing2;
				}else {
					 System.out.println("Flight search successful");
						return billing;
				}
		
		 
	}
	 public void setPotentialFailure(PotentialFailure potentialFailure) {
	        this.potentialFailure = potentialFailure;
	    }

}
