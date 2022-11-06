package com.example.BillingService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BillingService.dao.BillRepository;
import com.example.BillingService.entity.Billing;

@Service
public class BillingServiceImp implements BillingService{
	
	@Autowired
	public BillRepository billRepository;
	@Override
	public List<Billing> findAll() {
		return billRepository.findAll();
	}
	@Override
	public Billing insertBilling( Billing billing) {
		// TODO Auto-generated method stub
		return billRepository.save(billing);
	}
	@Override
	public Billing getBillingByPassengerId(int passengerId) {
		// TODO Auto-generated method stub
		return billRepository.getBillingByPassengerId(passengerId);
	}

}
