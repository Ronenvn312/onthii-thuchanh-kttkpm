package com.example.PassengerService.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
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
	
	private final String PASSENGER_CACHE = "PASSENGER";
	
	@Autowired
    RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Integer, Passenger> hashOperations;
    
    @PostConstruct
    private void intializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }
	@Override
	public List<Passenger> findAll() {
		List<Passenger> passengers = passengerRespository.findAll();
		return passengers;
	}
	@Override
	public Passenger insertPassenger(Passenger passenger) {
		hashOperations.put(PASSENGER_CACHE, passenger.getPassengerId(), passenger);
		return passengerRespository.save(passenger);
	}
	@Override
	public Passenger findById(int passengerId) {
		// TODO Auto-generated method stub
		return hashOperations.get(PASSENGER_CACHE, passengerId);
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
