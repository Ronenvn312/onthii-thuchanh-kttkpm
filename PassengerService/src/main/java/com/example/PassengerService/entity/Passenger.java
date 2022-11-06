package com.example.PassengerService.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Data
@Entity
@Table(name = "passenger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Passenger implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8796939005011086878L;
	@Id
	@Column(name = "PassengerId")
	private int passengerId;
	@Column(name = "Name")
	private String name;
	@Column(name = "address")
	private String address;
	
}
