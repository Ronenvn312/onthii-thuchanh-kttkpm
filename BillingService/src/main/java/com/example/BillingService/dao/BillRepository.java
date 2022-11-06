package com.example.BillingService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BillingService.entity.Billing;

@Repository
public interface BillRepository extends JpaRepository<Billing, Integer>{
	public Billing getBillingByPassengerId(int passenerId);
}
