package com.OnlineTrainBooking.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Generated;
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
public class Booking {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long bookingId;
		
		private String username;
		
		private int tno;
		
		private String name;
		
		private String fromplace;
			
	 	private String toplace;
		
		private int price;
		
		private LocalTime departure;
		
		private LocalTime arrival;
		
		private double totalCost;
		
		private String transactionId;
		
		private int passengers;
		
		private String email;
		
		private LocalDate date;
		
}
