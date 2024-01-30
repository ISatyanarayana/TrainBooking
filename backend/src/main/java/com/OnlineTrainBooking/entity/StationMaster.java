package com.OnlineTrainBooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String fromplace;
	
	private String toplace;
}