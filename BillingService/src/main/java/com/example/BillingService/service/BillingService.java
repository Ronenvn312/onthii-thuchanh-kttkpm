package com.example.BillingService.service;

import java.util.List;

import com.example.BillingService.entity.Billing;

public interface BillingService {
	public List<Billing>  findAll();
	public Billing insertBilling(Billing billing);
	public Billing getBillingByPassengerId(int passengerId);
}
