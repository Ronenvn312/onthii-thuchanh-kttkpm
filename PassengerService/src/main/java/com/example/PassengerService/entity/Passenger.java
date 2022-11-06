package com.example.PassengerService.entity;

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
@Table(name = "passenger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Passenger {
	@Id
	@Column(name = "PassengerId")
	private int passengerId;
	@Column(name = "Name")
	private String name;
	@Column(name = "address")
	private String address;
}
