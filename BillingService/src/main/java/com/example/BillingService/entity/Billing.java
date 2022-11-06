package com.example.BillingService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "billing")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Billing {
	
	@Id
	@Column(name = "BillId")
	private int billId;
	@Column(name = "Name")
	private String name;
	@Column(name = "Price")
	private double price;
	@Column(name = "PassengerId")
	private int passengerId;
	
}
