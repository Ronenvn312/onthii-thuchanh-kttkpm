package com.example.PassengerService.controller;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

import static java.time.temporal.ChronoUnit.SECONDS;

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
import com.example.PassengerService.service.failures.RateLimitFailNTimes;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.vavr.CheckedFunction0;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

	@Autowired
	private PassengerService passengerService;

	@GetMapping("/retry-search")
	public Billing retryPassengerService() {
		RetryConfig config = RetryConfig
				.<Billing>custom()
				.maxAttempts(3)
				.waitDuration(Duration.of(10, SECONDS))
				.build();
		RetryRegistry registry = RetryRegistry.of(config);
		 Retry retry = registry.retry("passengerServicec",config);
		 
		
		CheckedFunction0<Billing> retryingPassengerFindBill 
			= Retry.decorateCheckedSupplier(retry, () -> passengerService.httpSearchBill(17));
		Billing response;
			try {
				response = retryingPassengerFindBill.apply();
				return response;
			} catch (Throwable e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		return null;
	}
	@GetMapping("/retry-reate-limit")
	public Billing reateLimitPassengerService() {
		RetryConfig config = RetryConfig
				.custom()
				.maxAttempts(3)
				.waitDuration(Duration.of(2, SECONDS))
				.build();
		RetryRegistry registry = RetryRegistry.of(config);
		 Retry retry = registry.retry("passengerServicec",config);
		 Retry.EventPublisher publisher = retry.getEventPublisher();
		 publisher.onRetry(event -> System.out.println(event.toString()));
	     publisher.onSuccess(event -> System.out.println(event.toString()));
	      
	    passengerService.setPotentialFailure(new RateLimitFailNTimes(2));
	    
		CheckedFunction0<Billing> retryingPassengerFindBill 
			= Retry.decorateCheckedSupplier(retry, () -> passengerService.httpSearchBill(17));
		Billing response;
			try {
				response = retryingPassengerFindBill.apply();
				return response;
			} catch (Throwable e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		return null;
	}
	@GetMapping("/find-all")
	public List<Passenger> findAll() {
		return passengerService.findAll();
	}

	@PostMapping("/insert")
	public Passenger insertPassenger(@RequestBody Passenger passenger) {
		return passengerService.insertPassenger(passenger);
	}
	@GetMapping("/getid")
	public Passenger getPassenger(@RequestParam("id") int passengerID) {
		return passengerService.findById(passengerID);
	}
	@GetMapping("/billing")
	public Billing getBillingByPassengerId(@RequestParam("passengerId") int passengerId) {
		return passengerService.getBillingByPassengerId(passengerId);
	}
}
