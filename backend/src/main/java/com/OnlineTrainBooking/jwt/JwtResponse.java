package com.OnlineTrainBooking.jwt;



import com.OnlineTrainBooking.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	
	private User user;
	private String jwtToken;
}
