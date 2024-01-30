package com.OnlineTrainBooking.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Train {

	@Id
	private int tno;
	
	private String name;
	
	private String fromplace;
		
 	private String toplace;
	
	private String schedule;
	
	private int price;
	
	private LocalTime departure;
	
	private LocalTime arrival;
}
