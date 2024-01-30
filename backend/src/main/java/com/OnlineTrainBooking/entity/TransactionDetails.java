package com.OnlineTrainBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDetails {

		private String orderId;
		private int amount;
		private String currency;
		private String Key;
		
}
