package com.example.BillingService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BillingService.entity.Billing;
import com.example.BillingService.service.BillingService;
@RestController
@RequestMapping("/billing")
public class BillingController {
	@Autowired
	private BillingService billingService;
	
	@GetMapping("/find-all")
	public List<Billing> getAllBilling(){
		return billingService.findAll();
	} 
	@PostMapping("/insert")
	@ResponseBody
	public Billing insertBilling(@RequestBody Billing billing) {
		return billingService.insertBilling(billing);
	}
	
	@GetMapping("/find")
	@ResponseBody
	public Billing getBillingByPassengerId(@RequestParam("passengerId") int passenerId) {
		return billingService.getBillingByPassengerId(passenerId);
	}
}
